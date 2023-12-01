(ns day1
  (:require
   [clojure.string :as str]))

(def day1-input
  (->> (slurp "src/day1")
       (str/split-lines)))

(def digits
  {"one"   "1"
   "two"   "2"
   "three" "3"
   "four"  "4"
   "five"  "5"
   "six"   "6"
   "seven" "7"
   "eight" "8"
   "nine"  "9"})

(defn to-int [x]
  (if (not= nil (digits x))
    (digits x) x))

(def repattern-digits  
  (map #(apply str %) (map reverse (keys digits))))

(defn create-exp 
  [dig]
  (let [r (str "(" (str/join "|" dig) ")")]    
    (re-pattern (str "(?:" r "|(\\d))"))))

(def get-exp (create-exp (keys digits)))

(def get-rev-exp (create-exp repattern-digits))

(defn create-pair
  [coll]
  (let [fst  (ffirst (re-seq get-exp coll))
        scnd (ffirst (re-seq get-rev-exp (apply str (reverse coll))))]
    (list (to-int fst) (to-int (apply str (reverse scnd))))))

(defn calc
  [coll]
  (reduce (fn [acc x]         
            (+ acc (Integer/parseInt (str (first x) (last x))))) 0 coll))

(defn day1-part-one
  [coll]
  (calc (map #(re-seq #"\d" %) coll)))

(defn day1-part-two
  [coll]
  (calc (map create-pair coll)))

(comment  
  (day1-part-two day1-input)
  (day1-part-one day1-input))
    