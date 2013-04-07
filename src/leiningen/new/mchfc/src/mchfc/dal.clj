(ns {{name}}.dal
  (:use [rec-mong.core]
        [clojure.tools.logging]
        [{{name}}.model]
        )
  (:require [clojure.core.cache :as cache]
            [monger.collection :as coll])
  (:import [{{name}}.model User])
  )

; cache
(def account-cache (atom (cache/lru-cache-factory {})))
(def user-suppliers-cache (atom (cache/lru-cache-factory {})))

(defn through! [cache key value-fn]
  (if (cache/has? @cache key)
    (get (swap! cache #(cache/hit %1 %2) key) key)
    (get (swap! cache #(cache/miss %1 %2 %3) key (value-fn)) key)))
; 

(defn find-user [username]
  (try
    (if-let [user (first (query :kind User :filter {:username username}))]
      (update-in user [:roles] (fn [roles] (set (map keyword roles)))))
    (catch Exception e
      (error e e))))

(defn get-user [id]
  (retrieve User id))

(defn all-users []
  (query :kind User))
