(ns {{name}}.mgmt-view
  (:use [hiccup core def element page form]
        [clojure.tools.logging]
        )
  (:require [{{name}}.view :as v]))

(defn list-users [admin all-users]
  (v/template admin
    [:div.sixteen.columns
     [:table.mgmt
      [:thead
       [:tr
        [:th "action"]
        [:th "username"]
        [:th "name"]
        [:th "roles"]
        [:th "delete"]
        ]]
      [:tbody
       (for [u all-users]
         (let [{:keys [username name roles _id]} u]
           [:tr
            [:td [:a {:href (str "/admin/user/edit/" _id)} "edit"]]
            [:td username]
            [:td name ]
            [:td (pr-str roles)]
            [:td [:a {:href (str "/admin/user/delete/" _id)} "delete"]]
            ]))
       [:tr [:td "&nbsp;"]]
       [:tr
        [:td [:a {:href "/admin/user/add"} "add user"]]]
       ]
      ]
     ]
    ))

(defn user-add [admin]
  (v/template admin
    [:div.sixteen.columns
     (form-to {:id "user-mgmt"} [:post "/admin/user/save"]
              (label :name "name")
              (text-field :name)
              (label :username "username")
              (text-field :username)
              (label :passwd "password")
              (password-field :passwd)
              (label :passwd "password repeat")
              (password-field :passwdrpt)
              (radio-button "role" false "user") 
              (label :role-user "user") 
              (radio-button "role" false "admin") (label :role-admin "admin") 
              [:br]
              (submit-button "add")

       )]))

(defn user-edit [admin user]
  (v/template admin
    [:div.sixteen.columns
     (form-to [:post "/admin/user/save"]
       )

     ]
    )
  )
