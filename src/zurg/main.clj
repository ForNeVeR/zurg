(ns zurg.main
  (:use clojure.core.logic))

(defn -main [& args]
  (let [buzz 5
        woody 10
        rex 20
        hamm 25
        q (run* [q])]
    (println q)))
