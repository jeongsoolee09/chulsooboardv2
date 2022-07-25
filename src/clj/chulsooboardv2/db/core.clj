(ns chulsooboardv2.db.core
  (:require
   [next.jdbc.date-time]
   [next.jdbc.result-set]
   [next.jdbc :as jdbc]
   [mount.core :refer [defstate]]
   [chulsooboardv2.db.operations :as operations]
   [chulsooboardv2.config :refer [env]]))

(def ^:dynamic *db*
  (jdbc/get-datasource {:dbtype "postgres"
                        :dbname "chulsooboard"
                        :port 5432}))

(extend-protocol next.jdbc.result-set/ReadableColumn
  java.sql.Timestamp
  (read-column-by-label [^java.sql.Timestamp v _]
    (.toLocalDateTime v))
  (read-column-by-index [^java.sql.Timestamp v _2 _3]
    (.toLocalDateTime v))
  java.sql.Date
  (read-column-by-label [^java.sql.Date v _]
    (.toLocalDate v))
  (read-column-by-index [^java.sql.Date v _2 _3]
    (.toLocalDate v))
  java.sql.Time
  (read-column-by-label [^java.sql.Time v _]
    (.toLocalTime v))
  (read-column-by-index [^java.sql.Time v _2 _3]
    (.toLocalTime v)))

(defn insert-all-scrapped [scrapped]
  (doseq [song-maps scrapped
          song-map song-maps]
    (operations/insert-into-songboard! *db* song-map)))
