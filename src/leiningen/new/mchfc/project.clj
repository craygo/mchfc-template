(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/data.json "0.2.1"]
                 [com.novemberain/monger "1.4.2"]
                 [rec-mong "0.1.0-SNAPSHOT"]
                 [log4j "1.2.17" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [lib-noir "0.2.0"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [org.clojure/tools.logging "0.2.3"]
                 [com.cemerick/friend "0.1.3"]
                 [clj-time "0.4.5"]
                 ]
  :min-lein-version "2.0.0"
  )
