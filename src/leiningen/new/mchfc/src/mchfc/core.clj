(ns {{name}}.core
  (:use [{{name}}.model]
        [{{name}}.controller]
        [rec-mong.core]
        [compojure.core]
        [clojure.tools.logging]
        [ring.adapter.jetty]
        [ring.middleware stacktrace params nested-params keyword-params file file-info]
        [ring.middleware.session httpsession])
  (:require [monger.core :as mg]
            [compojure.route :as route]
            [noir.session :as sess]
            [noir.response :as resp :only [redirect]]
            [{{name}}.setup :as setup]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds]))
  )

(def default-base-url "http://wnl-l.appspot.com")

(defn wrap-authorize-2
  "Temp wrapper since friend/wrap-authorize doesn't work as advertised and throws exception
  instead of using default-unauthorized-handler"
  [handler roles]
  (fn [request]
    (if (friend/authorized? roles (friend/identity request))
      (handler request)
      {:status 403 :body "Sorry, you do not have access to this resource."})))

(defroutes app*
  (context "/user" req (wrap-authorize-2 #'user-routes #{:user}))
  (GET "/" req (index req))
  (GET "/req" req (pr-str req))
  (context "/admin" req (wrap-authorize-2 #'admin-routes #{:admin}))
  (GET "/login" [username login_failed] (login username login_failed))
  (friend/logout (ANY "/logout" request (resp/redirect "/")))
  (route/not-found "<h1>Page not found</h1>")
  )

(def app
  (-> app*
    wrap-stacktrace
    (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn #'{{name}}.dal/find-user)
                          :workflows [(workflows/interactive-form)]})
    wrap-keyword-params
    wrap-params
    wrap-nested-params
    (wrap-file "public")
    wrap-file-info
    (sess/wrap-noir-session {:session-store (http-session-store "sess-key")})
    ))

(defn -main [port & [url]]
  (let [base-url (or url default-base-url)
        mongo-uri (System/getenv "MONGOLAB_URI")]
    (info "-main base-url " base-url " mongo-uri " mongo-uri)
    (defonce conn (mg/connect-via-uri! mongo-uri))
    (info (time (setup/install)))
    (run-jetty #'app {:port (Integer. port) :join? false})))

;(-main 80)
