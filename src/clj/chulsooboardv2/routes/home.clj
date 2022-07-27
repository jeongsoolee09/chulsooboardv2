(ns chulsooboardv2.routes.home
  (:require
   [chulsooboardv2.layouts.core :refer [add-base-template]]
   [chulsooboardv2.db.core :as db]      ; TODO: will be integrated later
   [chulsooboardv2.middleware :as middleware]
   [hiccup.page :refer [doctype]]
   [ring.util.http-response :as response]))

;; We need to write some sample HTML first, and see what it looks like.
(def home-page
  (response/ok
   (add-base-template
    [:input {:type "text"
             :class "search song"
             :placeholder "Input a song title..."}
     :button {:class "submit songsubmit"}])))

(def about-page
  (response/ok
   (add-base-template
    [:img {:src "/img/warning_clojure.png"}])))

(def error-page
  (add-base-template
   (list (doctype :html5)
         [])))

(defn home-routes []
  ["" {:middleware [middleware/wrap-csrf
                    middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]])
