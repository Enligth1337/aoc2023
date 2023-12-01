(ns day1
  (:require
   [clojure.string :as str]))

(def day1-input
  (->> (slurp "/home/oleg/dev/aocClj2023/src/day1.clj/day1")
       (str/split-lines)
       (map #(re-seq #"\d" %))))

(def day1-parttwo-input
  (->> (slurp "/home/oleg/dev/aocClj2023/src/day1.clj/day1")
       (str/split-lines)
       (map #(re-seq #"(?:(one|two|three|four|five|six|seven|eight|nine)|(\d))" %))
       (map flatten)
       (map #(remove nil? %))))

(def somemap {"one" "1"
              "two" "2"
              "three" "3"
              "four" "4"
              "five" "5"
              "six" "6"
              "seven" "7"
              "eight" "8"
              "nine" "9"
              "zero" "0"})

(defn to-int [x]
  (if (not= nil (somemap x))
    (somemap x)
    x))

(defn calc
  [coll]
  (reduce (fn [acc x]
            (+ acc (Integer/parseInt (str (first x) (last x))))
            ;(spit "/home/oleg/dev/aocClj2023/src/day1.clj/test" (str (first x) (last x) "\n") :append true  )
            )0 coll))

(defn day1-1step
  [coll]
  (calc coll))

(defn day-2part
  [coll]
  (calc (map #(map to-int %) coll)))

(comment
  (map #(map to-int %) day1-parttwo-input)
  (Integer/parseInt "6548565485")
  (vec day1-parttwo-input)
  (map flatten day1-parttwo-input)
  (day1-1step day1-input)
  (spit "/home/oleg/dev/aocClj2023/src/day1.clj/test" (str/join "\n" (map vec day1-parttwo-input)))
  (day-2part day1-parttwo-input))
  

    
    


    