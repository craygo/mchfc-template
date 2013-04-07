(ns {{name}}.util
  (:use [clj-time.core :only [today-at-midnight minus days]]
        [clj-time.predicates :only [monday? tuesday? wednesday? thursday? friday? saturday? sunday?]]
        [clj-time.coerce :only [to-date]]
  ))

(defn monday-this-week []
  (let [today (today-at-midnight)
        res (cond 
              (monday? today) today
              (tuesday? today) (minus today (days 1))
              (wednesday? today) (minus today (days 2))
              (thursday? today) (minus today (days 3))
              (friday? today) (minus today (days 4))
              (saturday? today) (minus today (days 5))
              (sunday? today) (minus today (days 6)))]
    (to-date res)))

(defn str-to-long [s]
  (try
    (Long/parseLong s)
    (catch Exception e)))
