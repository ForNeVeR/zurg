(ns zurg.main
  (:use clojure.core.logic))

(def buzz 5)
(def woody 10)
(def rex 20)
(def hamm 25)

(def boundary 60)

(defn step [left-side right-side route]
  (conde
    ((== left-side []))
    ((membero :light left-side)
     (fresh [left-side2 right-side2 route2 traveler1 traveler2]
       (membero traveler1 left-side)
       (membero traveler2 left-side)
       (=/= traveler1 traveler2)
       ;; TODO: remove t1 and t2 from the left side
       (appendo right-side [traveler1 traveler2] right-side2)
       (appendo route [traveler2 traveler2] route2)
       (step left-side2 right-side2 route2)))
    ((membero :light right-side)
     (fresh [left-side2 right-side2 route2 traveler]
       (membero traveler right-side)
       (appendo left-side [:light traveler] left-side2)
       ;; TODO: Define right-side2 as the right-side without the traveler
       (appendo route [toy] route2)
       (step left-side2 right-side2 router2)))))

(defn do-solve []
  (run 1 [route]
    (step [:buzz :woody :rex :hamm :light] [] route)
    (fresh [time]
      ;; TODO: calculate travel time from the route variable
      (project [time]
        (== true (<= time boundary))))))

(defn -main [& args]
  (println (do-solve)))
