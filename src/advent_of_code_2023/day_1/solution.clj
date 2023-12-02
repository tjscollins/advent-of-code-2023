(ns advent-of-code-2023.day-1.solution
  (:require [clojure.string :as clojure.string]))

;; --- Day 1: Trebuchet?! ---
;;
;;Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you
;; a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
;;
;;You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
;;
;;Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second
;; puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
;;
;;You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you
;; ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the
;; sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a
;; trebuchet ("please hold still, we need to strap you in").
;;
;;As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been
;; amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are
;; having trouble reading the values on the document.
;;
;;The newly-improved calibration document consists of lines of text; each line originally contained a specific
;; calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining
;; the first digit and the last digit (in that order) to form a single two-digit number.
;;
;;For example:
;;
;;1abc2
;;pqr3stu8vwx
;;a1b2c3d4e5f
;;treb7uchet
;;In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
;; Consider your entire calibration document. What is the sum of all of the calibration values?

(defn filter-non-digits [input-str]
  (filter #(Character/isDigit ^char %) input-str))

(defn first-and-last [input-str]
  (let [digits (filter-non-digits input-str)]
    (str (first digits) (last digits))))

(defn part-1 [input-file]
  (let [input-str (slurp input-file)
        lines (clojure.string/split-lines input-str)]
    (reduce + (map #(Integer/parseInt %) (map first-and-last lines)))))

;; --- Part Two ---
;;Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two,
;; three, four, five, six, seven, eight, and nine also count as valid "digits".
;;
;;Equipped with this new information, you now need to find the real first and last digit on each line. For example:
;;
;;two1nine
;;eightwothree
;;abcone2threexyz
;;xtwone3four
;;4nineeightseven2
;;zoneight234
;;7pqrstsixteen
;;In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
;;
;;What is the sum of all of the calibration values?

(defn contains-digit-word [input-str]
  (some #(clojure.string/includes? input-str %) ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"]))

(defn convert-digit-word-to-digit [input-str]
  (cond
    (clojure.string/includes? input-str "one") "1"
    (clojure.string/includes? input-str "two") "2"
    (clojure.string/includes? input-str "three") "3"
    (clojure.string/includes? input-str "four") "4"
    (clojure.string/includes? input-str "five") "5"
    (clojure.string/includes? input-str "six") "6"
    (clojure.string/includes? input-str "seven") "7"
    (clojure.string/includes? input-str "eight") "8"
    (clojure.string/includes? input-str "nine") "9"))

(defn first-digit [input-str]
  (loop [idx 0
         prefix ""]
    (let [char (nth input-str idx)]
      (if (Character/isDigit ^char char)
        (str char)
        (let [new-prefix (str prefix char)]
          (if (contains-digit-word new-prefix)
            (convert-digit-word-to-digit new-prefix)
            (recur (inc idx) new-prefix)))))))

(defn last-digit [input-str]
  (loop [idx (dec (count input-str))
         suffix ""]
    (let [char (nth input-str idx)]
      (if (Character/isDigit ^char char)
        (str char)
        (let [new-suffix (str char suffix)]
          (if (contains-digit-word new-suffix)
            (convert-digit-word-to-digit new-suffix)
            (recur (dec idx) new-suffix)))))))

(defn part-2 [input-file]
  (let [input-str (slurp input-file)
        lines (clojure.string/split-lines input-str)]
    (reduce + (map #(Integer/parseInt %) (map #(str (first-digit %) (last-digit %)) lines)))))

