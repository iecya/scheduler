(ns scheduler.core-test
  (:require [clojure.test :refer :all]
            [scheduler.core :refer :all]
            [scheduler.utils :as utils]))

(deftest time-input-test
  (testing "Given a time input, verifies it is in the valid format"
    (is (nil? (utils/validate-time-input "24:50")))
    (is (nil? (utils/validate-time-input "03:60")))
    (is (utils/validate-time-input "11:35"))
    (is (utils/validate-time-input "05:59"))))
