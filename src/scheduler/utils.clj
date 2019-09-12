(ns scheduler.utils)

(defn validate-time-input
  [input]
  (when (seq input) (re-matches #"(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]" input)))

(defn validate-line
  [line]
  (when (seq line) (re-matches #"([0-5][0-9]|\*)\s([0-1][0-9]|2[0-3]|\*)\s/bin/(\w+)" line)))

(defn star?
  [value]
  (= "*" value))
