(ns zurg.main
  (:use clojure.core.logic))

(def buzz 5)
(def woody 10)
(def rex 20)
(def hamm 25)

(def boundary 60)

(defn do-solve []
  (run* [route]
    (fresh [time]
      (conde
        ((== time 30) (== route 1))
        ((== time 60) (== route 2))
        ((== time 90) (== route 3)))
      (project [time]
        (== true (<= time boundary))))))

(defn -main [& args]
  (println (do-solve)))
