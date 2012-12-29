(ns zurg.main
  (:use clojure.core.logic))

(def buzz 5)
(def woody 10)
(def rex 20)
(def hamm 25)

(def boundary 60)

(defn do-solve []
  (run* [q]
    (project [q]
      (== true (< q boundary)))))

(defn -main [& args]
  (println (do-solve)))
