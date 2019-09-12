(ns scheduler.core
  (:require [scheduler.utils :as utils]
            [clojure.string :as string]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io])
  (:gen-class))



(defn star-num->output
  [[hh-str mm] HH-str script]
  (let [HH (Integer. HH-str)
        hh (Integer. hh-str)]
    (cond
      (= hh HH)
      (utils/format-output (str HH-str ":" mm) "today" script)

      (< hh HH)
      (utils/format-output (str HH-str ":00") "today" script)

      (> hh HH)
      (utils/format-output (str HH-str ":00") "tomorrow" script))))


(defn get-next-job-run
  [time line]
  (let [[MM HH script] (string/split line #" ")]
    (cond
      (and (utils/star? MM) (utils/star? HH))
      (str time " today - " script)

      (and (utils/star? MM) (utils/num? HH))
      (star-num->output (utils/split-time time) HH script)

      :else [time line])))

(defn process-line
  [time line]
  (cond
    (= "exit" line)
    (System/exit 0)

    (nil? (utils/validate-line line))
    (println (str "The following line has an invalid format: " line " - format should be 'MM|* HH|* <path/to/script>'"))

    :default
    (println line)))

(defn -main
  "I don't do a whole lot ... yet."
  [time]
  ; validate time input - not nil and format HH:MM
  (let [validated-time (utils/validate-time-input time)]
    (if (nil? validated-time)
      (println "Please input a valid time in HH:MM format")

      ; read input
      (doseq [line (line-seq (io/reader *in*))]
        (process-line time line)))))
