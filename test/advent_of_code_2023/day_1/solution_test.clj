(ns advent-of-code-2023.day-1.solution-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [advent-of-code-2023.day-1.solution :refer [first-digit last-digit]]))

(deftest first-digit-test
  (testing "first-digit"
    (is (= (first-digit "1abc2") "1"))
    (is (= (first-digit "pqr3stu8vwx") "3"))
    (is (= (first-digit "a1b2c3d4e5f") "1"))
    (is (= (first-digit "treb7uchet") "7"))
    (is (= (first-digit "two1nine") "2"))
    (is (= (first-digit "eightwothree") "8"))
    (is (= (first-digit "abcone2threexyz") "1"))
    (is (= (first-digit "xtwone3four") "2"))
    (is (= (first-digit "4nineeightseven2") "4"))))

(deftest last-digit-test
  (testing "last-digit"
    (is (= (last-digit "1abc2") "2"))
    (is (= (last-digit "pqr3stu8vwx") "8"))
    (is (= (last-digit "a1b2c3d4e5f") "5"))
    (is (= (last-digit "treb7uchet") "7"))
    (is (= (last-digit "two1nine") "9"))
    (is (= (last-digit "eightwothree") "3"))
    (is (= (last-digit "abcone2threexyz") "3"))
    (is (= (last-digit "xtwone3four") "4"))
    (is (= (last-digit "4nineeightseven2") "2"))))

