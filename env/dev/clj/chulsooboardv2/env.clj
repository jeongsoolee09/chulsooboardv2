(ns chulsooboardv2.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [chulsooboardv2.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[chulsooboardv2 started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[chulsooboardv2 has shut down successfully]=-"))
   :middleware wrap-dev})
