(ns chulsooboardv2.routes.home
  (:require
   [chulsooboardv2.layouts.core :refer [add-base-template wrap-as-html]]
   [hiccup.page :refer [doctype]]
   [compojure.core :refer [defroutes context GET POST]]
   [ring.util.http-response :as response]
   [ring.mock.request :as mock]))

(defn home-page [_]
  (response/ok
   (add-base-template
     (list [:input {:type "text"
                    :class "search song"
                    :placeholder "Input a song title..."}]
           [:button {:class "submit songsubmit"}
            "Submit!"]))))

(defn about-page [_]
  (response/ok
   (add-base-template
    [:img {:src "/img/warning_clojure.png"}])))

(defn error-page [_]
  (add-base-template
   (list (doctype :html5)
         [:div "ahahahahah"])))

(defroutes home-routes
  (GET "/" [] (wrap-as-html home-page))
  (GET "/about" [] (wrap-as-html about-page)))
