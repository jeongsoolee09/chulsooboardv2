(ns chulsooboardv2.db.operations
  (:require
   [honey.sql :as sql]
   [honey.sql.helpers :refer [create-table with-columns
                              insert-into columns values
                              select from where]]
   [next.jdbc :as jdbc]))

(defn create-songboard! [db]
  (jdbc/execute! db (-> (create-table "songboard")
                        (with-columns [[:id :serial [:primary-key]]
                                       [:year :int]
                                       [:month :int]
                                       [:day :int]
                                       [:artist [:varchar 255]]
                                       [:title [:varchar 255] [:not nil]]])
                        (sql/format {:quote :ansi}))))

(defn insert-into-songboard! [db song-map]
  (jdbc/execute! db (-> (insert-into :songboard)
                        (values [song-map])
                        (sql/format {:quote :ansi}))))

(defn select-from-songboard-where-title
  [db title]
  (jdbc/execute! db (-> (select [:song])
                        (from :songboard)
                        (where [:= :song.title title])
                        (sql/format {:quote :ansi}))))

(defn select-from-songboard-where-artist
  [db artist]
  (jdbc/execute! db (-> (select [:song])
                        (from :songboard)
                        (where [:= :song.artist artist])
                        (sql/format {:quote :ansi}))))

(defn select-from-songboard-where-title-and-artist
  [db title artist]
  (jdbc/execute! db (-> (select [:song])
                        (from :songboard)
                        (where [[:= :song.title title]
                                [:= :song.artist artist]])
                        (sql/format {:quote :ansi}))))

(comment
  (require '[clojure.repl :refer :all])
  (require '[chulsooboardv2.db.core :refer [*db*]])
  (require '[chulsooboardv2.db.scrape :refer [scrape-upto-number]])

  (def scrapped (scrape-upto-number 1))

  (def song-maps user/song-maps)

  (defn insert-all-scrapped [scrapped]
    (doseq [song-maps scrapped
            song-map song-maps]
      (insert-into-songboard! *db* song-map)))

  (insert-all-scrapped (scrape-upto-number 540)))
