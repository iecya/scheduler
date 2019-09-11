(ns scheduler.core
  (:require [scheduler.utils :as utils]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io])
  (:gen-class))


(defn process-line
  [line]
  (if (= "exit" line)
    (System/exit 0)
    (println line)))

(defn -main
  "I don't do a whole lot ... yet."
  [time]
  ; validate time input - not nil and format HH:MM
  (let [validated-time (utils/validate-time-input time)]
    (if (nil? validated-time)
      (println "Please input a valid time in HH:MM format")

      ; read input
      (with-open [reader (io/reader *in*)]
        (println "Please insert your input:")
        (doall (map process-line (line-seq reader)))))))
