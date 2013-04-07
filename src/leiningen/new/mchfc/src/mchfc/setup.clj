(ns {{name}}.setup
  (:use [{{name}}.model]
        [rec-mong.core])
  (:require [cemerick.friend.credentials :as creds]
            [{{name}}.util :as util])
  (:import [{{name}}.model User]))


(defn install []
  (when (empty? (query :kind User))
    (let [harry (save (new-user "harrybin" (creds/hash-bcrypt "foo") #{:user} "Harry Binnendijk" ))
          admin (save (new-user "admin" (creds/hash-bcrypt "admin") #{:admin} "Administrator" )) ]
      )))

#_(do
  (remove-all User)
  (time (install)))
