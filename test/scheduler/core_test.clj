(ns scheduler.core-test
  (:require [clojure.test :refer :all]
            [scheduler.core :refer :all]
            [scheduler.utils :as utils]))

(deftest time-input-test
  (testing "Given a time input, verifies it is in the valid format"
    (is (utils/validate-time-input "24:50"))))
