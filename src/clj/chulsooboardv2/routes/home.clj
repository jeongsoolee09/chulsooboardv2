(ns chulsooboardv2.routes.home
  (:require
   [chulsooboardv2.layouts.core :refer [add-base-template]]
   [hiccup.page :refer [doctype]]
   [compojure.core :refer [defroutes context GET POST]]
   [ring.util.http-response :as response]
   [ring.mock.request :as mock]))

(defn home-page [_]
  (response/ok
   (add-base-template
    [:input {:type "text"
             :class "search song"
             :placeholder "Input a song title..."}
     :button {:class "submit songsubmit"}])))

(defn about-page [_]
  (response/ok
   (add-base-template
    [:img {:src "/img/warning_clojure.png"}])))

(defn error-page [_]
  (add-base-template
   (list (doctype :html5)
         [:div "ahahahahah"])))

(defroutes home-routes
  (GET "/" [] home-page)
  (GET "/about" [] about-page))
