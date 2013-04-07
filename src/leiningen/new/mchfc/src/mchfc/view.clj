(ns {{name}}.view
  (:use [hiccup core def element page form]
        [clojure.tools.logging]
        )
  (:require [{{name}}.util :as util]))

(defn menu [user]
  (if user
    [:nav.navigation
     [:ul
      [:div.seven.columns
       [:li [:a {:href "#"} "Get started"]]]
      ]
     ]))

(defn template [user & content]
  (html5 {:lang "en"}
         "<!--[if lt IE 7 ]><html class=\"ie ie6\" lang=\"en\"> <![endif]-->"
         "<!--[if IE 7 ]><html class=\"ie ie7\" lang=\"en\"> <![endif]-->"
         "<!--[if IE 8 ]><html class=\"ie ie8\" lang=\"en\"> <![endif]-->"
         "<!--[if (gte IE 9)|!(IE)]><!-->"
         [:head
          [:meta {:charset "utf-8"}]
          [:title "{{name}}"]
          [:meta {:content "", :name "description"}]
          [:meta {:content "", :name "author"}]
          [:meta
           {:content "width=device-width, initial-scale=1, maximum-scale=1",
            :name "viewport"}]
          (include-css "/css/screen.css")
          (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js")
          "<!--[if lt IE 9]><script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script><![endif]-->"
          [:link {:href "images/favicon.ico", :rel "shortcut icon"}]
          [:link
           {:href "images/apple-touch-icon.png", :rel "apple-touch-icon"}]
          [:link
           {:href "images/apple-touch-icon-72x72.png",
            :sizes "72x72",
            :rel "apple-touch-icon"}]
          [:link
           {:href "images/apple-touch-icon-114x114.png",
            :sizes "114x114",
            :rel "apple-touch-icon"}]]
         [:body
          [:div.container
           [:div.sixteen.columns.header
            [:div.sixteen.columns
             [:ul.profile
              [:li [:a {:href "#"} "Help"]]
              [:li " | "]
              [:li (if user
                     [:a {:href "/logout"} "Sign out"]
                     [:a {:href "/login"} "Sign in"])]
              (if user
                [:li " | "])
              (if user
                [:li [:a {:href "#"} "My info"]])
              (if user
                [:li " | "])
              (if user
                [:li (:name user)])]]
            (menu user)
            ]
           content
           [:div.sixteen.columns
            [:p ]
            ]
           ]]))

(defn index []
  (template nil
    [:div.sixteen.columns
     [:div.main
      [:h1 "{{name}}"]
      [:p "Please sign in to your account"]
      ]]))

(defn login [username login_failed]
  (template nil
    [:div.sixteen.columns
     [:div.main
      (form-to [:post "/login"]
               (if (= login_failed "Y") "Login failed")
               (label "username" "Username")
               (text-field {:autofocus true :autocomplete "off"} "username" username)
               (label "password" "Password")
               (password-field "password")
               (submit-button "Login")
               )]]))

(defn user [user]
  (template user
    [:div.sixteen.columns
     [:div.main
      [:h1 "{{name}}"]
      [:p "Welcome user"]
      ]]))

(defn admin [user]
  (template user
    [:div.sixteen.columns
     [:div.main
      [:h1 "{{name}}"]
      [:p "Welcome admin"]
      ]]))
