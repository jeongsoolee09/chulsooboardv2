(ns chulsooboardv2.core
  (:require
   [chulsooboardv2.handler :refer [app]]
   [ring.middleware.reload :refer [wrap-reload]]
   ;; unused requires... these or something similar might be used later
   [chulsooboardv2.nrepl :as nrepl]
   [luminus.http-server :as http]
   [luminus-migrations.core :as migrations]
   [chulsooboardv2.config :refer [env]]
   [clojure.tools.cli :refer [parse-opts]]
   [clojure.tools.logging :as log]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main []
  (run-jetty (wrap-cors
               (wrap-reload app)) {:port 80}))
