(ns chulsooboardv2.layouts.core
  (:require
   [clojure.java.io]
   [hiccup.core :as html]
   [hiccup.page :refer [doctype]]
   [garden.core :refer [css]]
   [ring.util.http-response :refer [ok]]
   [ring.util.anti-forgery :refer [anti-forgery-field]]
   [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
   [ring.util.response]))

(defn add-base-template
  "The base template."
  [subtemplate]
  (list (doctype :html5)
        [:html
         [:head
          [:meta {:charset "UTF-8"}]
          [:meta {:name "viewport"
                  :content "width=device-width, initial-scale=1"}]
          [:title "배철수의 음악캠프 팬페이지"]]
         [:body
          [:nav {:class "navbar is-info"}
           [:div {:class "container"}
            [:div {:class "navbar-brand"}
             [:a {:class "navbar-item"
                  :href "/"
                  :style "font-weight:bold;"}
              "배철수의 음악캠프"]
             [:span {:class "navbar-burger burger"
                     :data-target "nav-menu"}
              [:span]
              [:span]
              [:span]]]
            [:div {:id "nav-menu"
                   :class "navbar-menu"}
             [:div {:class "navbar-start"}]]]]
          [:section {:class "section"}
           [:div {:class "container"}
            subtemplate]]
          [:script {:type "text/javascript"}
           "(function() {
              var burger = document.querySelector('.burger');
              var nav = document.querySelector('#'+burger.dataset.target);
              burger.addEventListener}('click', function(){
                burger.classList.toggle('is-active');
                nav.classList.toggle('is-active');
          });
        })();"]]]))

(defn error-page                        ; DEPRECATED
  "error-details should be a map containing the following keys:
   :status - error status
   :title - error title (optional)
   :message - detailed error message (optional)

   returns a response map with the error page as the body
   and the status specified by the status key"
  [error-details])
