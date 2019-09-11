(ns scheduler.utils)

(defn validate-time-input
  [input]
  (when (seq input) (re-matches #"(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]" input)))
