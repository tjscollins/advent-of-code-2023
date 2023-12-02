(ns advent-of-code-2023.day-2.solution-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2023.day-2.solution :refer [parse-round parse-game round-possible? game-possible?]])
  (:import (advent_of_code_2023.day_2.solution Bag Game Round)))


(deftest parse-round-test
  (testing "parse-round"
    (is (= (parse-round "3 blue, 4 red") (Round. 3 0 4)))
    (is (= (parse-round "1 red, 2 green, 6 blue") (Round. 6 2 1)))
    (is (= (parse-round "2 green") (Round. 0 2 0)))
    (is (= (parse-round "8 green, 6 blue, 20 red") (Round. 6 8 20)))))

(deftest parse-game-test
  (testing "parse-game"
    (is (= (parse-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
           (Game. 1 [(Round. 3 0 4) (Round. 6 2 1) (Round. 0 2 0)])))
    (is (= (parse-game "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
           (Game. 4 [(Round. 6 1 3) (Round. 0 3 6) (Round. 15 3 14)])))))

(deftest round-possible?-test
  (testing "round-possible?"
    (is (= (round-possible? (Round. 3 0 4) (Bag. 3 0 4)) true))
    (is (= (round-possible? (Round. 6 2 1) (Bag. 8 3 2)) true))
    (is (= (round-possible? (Round. 0 2 0) (Bag. 10 10 10)) true))
    (is (= (round-possible? (Round. 6 8 20) (Bag. 5 100 100)) false))
    (is (= (round-possible? (Round. 6 8 20) (Bag. 100 5 100)) false))
    (is (= (round-possible? (Round. 6 8 20) (Bag. 100 100 5)) false))))
