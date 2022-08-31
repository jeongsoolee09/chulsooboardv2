(ns chulsooboardv2.handler
  (:require
   [chulsooboardv2.middleware :as middleware]
   [chulsooboardv2.layouts.core :refer [error-page]]
   [chulsooboardv2.routes.home :refer [home-routes]]
   [reitit.ring :as ring]
   [ring.middleware.content-type :refer [wrap-content-type]]
   [ring.middleware.webjars :refer [wrap-webjars]]))

;; TODO to be integrated later... study: https://www.metosin.fi/blog/reitit-ring/
(def app-routes
  (ring/ring-handler
   (ring/create-default-handler
    {:not-found
     (constantly (error-page {:status 404, :title "404 - Page not found"}))
     :method-not-allowed
     (constantly (error-page {:status 405, :title "405 - Not allowed"}))
     :not-acceptable
     (constantly (error-page {:status 406, :title "406 - Not acceptable"}))})
   home-routes))

(def app (middleware/wrap-base home-routes))
