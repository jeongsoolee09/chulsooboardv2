(ns chulsooboardv2.db.scrape
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]
            [chulsooboardv2.utils :refer [update-all-values]])
  (:gen-class))

(defn- unescape-HTML-special-characters [string]
  (str/escape string {"&lt;" \<
                      "&gt;" \>
                      "&amp;" \&}))

(defn- escape-quotes [string]
  (str/escape string {\" "\""
                      \' "\\'"}))

(defn- normalize-string
  "strip away the extra trailing whitespace,
   and deal with HTML special characters"
  [string]
  (-> (str/trim string)
      (unescape-HTML-special-characters)
      (escape-quotes)))

(defn- parse-only-integer
  "\"2007년\" |-> 2007"
  [string-with-numbers]
  (when-let [matches (take-while
                      #(re-matches #"[0-9]+" (str %))
                      string-with-numbers)]
    (Integer/parseInt (apply str matches))))

(defn- ymd-list-to-hashmap
  "(2007 8 27) |-> {:year 2007 :month 8 :day 27}"
  [ymd-list]
  {:year (nth ymd-list 0)
   :month (nth ymd-list 1)
   :day (nth ymd-list 2)})

(defn- parse-date-string
  "Parse the date string,
   e.g. 2007년 8월 27일 월요일 |->
        {:year 2007 :month 8 :day 27}"
  [date-string]
  (->> date-string
       (#(str/split % #" "))
       (take 3)
       (map parse-only-integer)
       (ymd-list-to-hashmap)))

(defn- html-doc->raw-table [html-doc]
  (->> html-doc
       (#(html/select % [:tr :td]))
       (map :content)
       (partition 4)))

(defn- process-row-of-raw-table
  "turn a raw row of a songboard into a map."
  [row]
  (let [row-artist (-> row
                       (nth 2)
                       (first)
                       (:content)
                       (first))

        row-title (-> row
                      (second)
                      (first)
                      (:content)
                      (first))]
    {:title row-title :artist row-artist}))

(defn- html-doc->songboard [html-doc]
  (->> html-doc
       (html-doc->raw-table)
       (mapv process-row-of-raw-table)
       (mapv (partial update-all-values normalize-string))))

(defn- board-number->html-doc [number]
  (let [url (str/join
             ["http://miniweb.imbc.com/Music/View?seqID="
              number
              "&progCode=RAMFM300"])]
    (html/html-resource (java.net.URL. url))))

(defn- html-doc->date-string [html-doc]
  (-> (html/select html-doc
                   [:body :div :div :div :div :p])
      (first)
      (:content)
      (first)))

(defn scrape-by-number [number]
  (let [html-doc (board-number->html-doc number)
        songboard (html-doc->songboard html-doc)
        date-string (html-doc->date-string html-doc)
        date-info-parsed (parse-date-string date-string)]
    (map (partial merge date-info-parsed) songboard)))

(defn scrape-upto-number [number]
  (loop [cnt number
         acc []]
    (if (< cnt 0)
      acc
      (let [complete-songboard (try (scrape-by-number cnt)
                                    (catch Exception _ nil))] ; Maybe the URL is invalid
        (if complete-songboard
          (do
            (println cnt)  ;; for logging purposes
            (recur (dec cnt) (conj acc complete-songboard)))
          (recur (dec cnt) acc))))))

;; TODO {:title "Non Je Ne Regrette Rien (화장품 \\'미샤\\' 광고 배경음악)", :artist "Edith Piaf"}
