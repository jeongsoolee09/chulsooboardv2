(ns chulsooboardv2.core
  (:require
   [chulsooboardv2.handler :refer [app]]
   ;; unused requires... these or something similar might be used later
   [chulsooboardv2.nrepl :as nrepl]
   [luminus.http-server :as http]
   [luminus-migrations.core :as migrations]
   [chulsooboardv2.config :refer [env]]
   [clojure.tools.cli :refer [parse-opts]]
   [clojure.tools.logging :as log]
   [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main []
  (run-jetty app {:port 8888}))
