(ns chulsooboardv2.utils)

(defn update-all-values
  "update all values of a given map with the supplied function."
  [f map]
  (reduce (fn [acc [key value]]
            (assoc acc key (f value))) {} (into [] map)))
