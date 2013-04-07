(ns {{name}}.friend
  "Friend helper functions"
  (:require [cemerick.friend :as friend]))

(defn user [req]
  (if-let [friend-identity (friend/identity req)]
    ((:authentications friend-identity) (:current friend-identity))))

(defn user? [req]
  (friend/authorized? #{:user} (friend/identity req)))

(defn admin? [req]
  (friend/authorized? #{:admin} (friend/identity req)))

