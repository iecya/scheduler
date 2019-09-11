(ns scheduler.core
  (:require [scheduler.utils :as utils])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [time]
  ; validate time input (not nil and format HH:MM
  (let [validated-time (utils/validate-time-input time)]
    (if (nil? validated-time)
      "Please input a valid time in HH:MM format"))
  ; read input
  )

