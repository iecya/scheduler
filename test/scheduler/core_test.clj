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

(deftest validate-line-test
  (testing "Given a line input, verifies it is in the valid format"
    (testing "Failing formats"
      (is (nil? (utils/validate-line "60 03 /bin/my_schedule_script")))
      (is (nil? (utils/validate-line "00 24 /bin/my_schedule_script")))
      (is (nil? (utils/validate-line "30 05 /bin/")))
      (is (nil? (utils/validate-line "00 00 /my_schedule_script"))))

    (testing "valid formats"
      (is (utils/validate-line "00 03 /bin/daily_job_script"))
      (is (utils/validate-line "59 23 /bin/hourly_job_script"))
      (is (utils/validate-line "00 00 /bin/weekly_job_script")))))
