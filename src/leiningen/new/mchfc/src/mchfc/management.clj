(ns {{name}}.management
  (:use [clojure.tools.logging]
        [compojure.core]
        [{{name}}.model]
        [rec-mong.core]
        )
  (:require [{{name}}.dal :as dal]
            [{{name}}.mgmt-view :as mv]
            [{{name}}.friend :as fh]
            [cemerick.friend.credentials :as creds])
  (:import [{{name}}.model User])
  )

(defn list-users [req]
  (let [admin (fh/user req)]
    (mv/list-users admin (dal/all-users))
    ))

(defn user-add [req]
  (let [admin (fh/user req)]
    (mv/user-add admin)))

(defn user-edit [req user-id]
  (let [admin (fh/user req)
        user (dal/get-user user-id)]
    (info "user-edit " user-id user)
    (mv/user-edit admin user)))

(defn user-delete [req user-id]
  (let [admin (fh/user req)]
    (if-let [user (dal/get-user user-id)]
      (pr-str (delete User (id user))))))

(defn user-save [req]
  (let [admin (fh/user req)
        {:keys [name username passwd passwdrpt role]} (:params req)]
    (if (contains? (set (map empty? [name username passwd passwdrpt role])) true)
      "missing some params"
      (if-not (= passwd passwdrpt)
        "password mismatch"
         (pr-str (save (new-user username (creds/hash-bcrypt passwd) (hash-set (keyword role)) name)))))))

(defroutes mgmt-routes
  (GET "/" req (list-users req))
  (GET "/user/add" req (user-add req))
  (GET "/user/edit/:id" req (user-edit req (:id (:params req))))
  (GET "/user/delete/:id" req (user-delete req (:id (:params req))))
  (POST "/user/save" req (user-save req))
  (GET "/mgmt" req (pr-str req))
  )
