(ns {{name}}.model
  )

; User - user of the system
(defrecord User [username password roles name])
 
(defn new-user [username password roles name]
  (map->User {:username username :password password :roles roles :name name}))

