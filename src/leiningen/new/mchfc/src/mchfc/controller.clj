(ns {{name}}.controller
  (:use [clojure.tools.logging]
        [compojure.core]
        [rec-mong.core])
  (:require [noir.session :as sess]
            [noir.response :as resp :only [redirect]]
            [{{name}}.view :as v]
            [{{name}}.dal :as dal]
            [{{name}}.model :as model]
            [{{name}}.friend :as fh]
            [{{name}}.management :as mgmt]
            [{{name}}.util :as util]
            [ring.middleware.nested-params :as np]
            ))

(defn index [req]
  (let [user (fh/user req)] 
    (if user
      (cond
        (fh/user? req) (v/user user)
        (fh/admin? req) (v/admin user)
        true (v/index))
      (v/index))))

(defn login [username login_failed]
  (v/login username login_failed))

(defroutes user-routes
  (GET "/" req (index req))
  )

(defroutes admin-routes
  (GET "/" req (index req))
  mgmt/mgmt-routes
  )

