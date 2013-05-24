(defproject rest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [liberator "0.8.0"]
                 [ring/ring-json "0.1.2"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler rest.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
