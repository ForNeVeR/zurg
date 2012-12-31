(ns zurg.main
  (:use clojure.core.logic))

(def buzz 5)
(def woody 10)
(def rex 20)
(def hamm 25)

(def boundary 60)

(defn rembero [x ls out]
  (conde
    ((== [] ls) (== [] out))
    ((fresh [a d]
       (conso a d ls)
       (== a x)
       (== d out)))
    ((fresh [a d res]
       (conso a d ls)
       (!= a x)
       (conso a res out)
       (rembero x d res)))))

(defn removeo [elements ys out]
  (conde
    ((== elements [])
     (== out ys))
    ((fresh [head tail res]
       (conso head tail elements)
       (rembero head ys res)
       (removeo tail res out)))))

(defn step [left-side right-side route out]
  (conde
    ((== left-side [])
     (== out route))
    ((membero :light left-side)
     (fresh [left-side2 right-side2 route2 traveler1 traveler2]
       (membero traveler1 left-side)
       (membero traveler2 left-side)
       (!= traveler1 :light)
       (!= traveler2 :light)
       (!= traveler1 traveler2)
       (removeo [traveler1 traveler2 :light] left-side left-side2)
       (appendo right-side [traveler1 traveler2 :light] right-side2)
       (appendo route [[traveler1 traveler2]] route2)
       (step left-side2 right-side2 route2 out)))
    ((membero :light right-side)
     (fresh [left-side2 right-side2 route2 traveler]
       (membero traveler right-side)
       (!= traveler :light)
       (appendo left-side [:light traveler] left-side2)
       (removeo [traveler :light] right-side right-side2)
       (appendo route [traveler] route2)
       (step left-side2 right-side2 route2 out)))))

(defn do-solve []
  (run 1 [route]
    (step [:buzz :woody :rex :hamm :light] [] route)
    (fresh [time]
      ;; TODO: calculate travel time from the route variable
      (== time 60)
      (project [time]
        (== true (<= time boundary))))))

(defn -main [& args]
  (println (do-solve)))
