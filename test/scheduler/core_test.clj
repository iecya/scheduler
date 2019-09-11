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


(deftest line-result
  (testing "For a given line and 'current_time' return the appropriate time and day of the next job"
    (is (= (get-next-job-run "10:00" "30 1 /bin/run_me_daily")
           "1:30 tomorrow - /bin/run_me_daily"))

    (is (= (get-next-job-run "12:25" "45 * /bin/run_me_hourly")
           "12:45 today - /bin/run_me_hourly"))

    (is (= (get-next-job-run "13:36" "* * /bin/run_me_every_minute")
           "13:36 today - /bin/run_me_daily"))

    (is (= (get-next-job-run "18:15" "* 19 /bin/run_me_sixty_times")
           "19:00 today - /bin/run_me_sixty_times"))

    (is (= (get-next-job-run "20:40" "* 19 /bin/run_me_sixty_times")
           "19:00 tomorrow - /bin/run_me_sixty_times"))

    (is (= (get-next-job-run "19:17" "* 19 /bin/run_me_sixty_times")
           "19:17 today - /bin/run_me_sixty_times"))

    (is (= (get-next-job-run "00:00" "30 1 /bin/run_me_daily")
           "1:30 today - /bin/run_me_daily"))))
