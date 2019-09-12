(ns scheduler.core
  (:require [scheduler.utils :as utils]
            [clojure.string :as string]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io])
  (:gen-class))



(defn star-num->output
  [[hh mm] HH script]
  (let [day (if (> (Integer. hh) (Integer. HH)) "tomorrow" "today")]
    (if (= hh HH)
      (utils/format-output (str HH ":" mm) day script)
      (utils/format-output (str HH ":00") day script))))

(defn num-star->output
  [[hh-str mm-str] MM-str script]
  (let [hh (Integer. hh-str)
        mm (Integer. mm-str)
        MM (Integer. MM-str)]
    (cond
      (<= mm MM)
      (utils/format-output (str hh-str ":" MM-str) "today" script)

      (and (> mm MM)
           (> hh 22))
      (utils/format-output (str "00:" MM-str) "tomorrow" script)

      (and (> mm MM)
           (< hh 23))
      (utils/format-output (str (inc hh) ":" MM-str) "today" script))))

(defn get-next-job-run
  [time line]
  (let [[MM HH script] (string/split line #" ")]
    (cond
      (and (utils/star? MM) (utils/star? HH))
      (str time " today - " script)

      (and (utils/star? MM) (utils/num? HH))
      (star-num->output (utils/split-time time) HH script)

      (and (utils/num? MM) (utils/star? HH))
      (num-star->output (utils/split-time time) MM script)

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
