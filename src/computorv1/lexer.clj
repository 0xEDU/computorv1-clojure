(ns computorv1.lexer)

(def decimal-base 10)
(def indeterminate \x)

(defn is-number?
  [c]
  (not (== (Character/digit c decimal-base) -1)))

(defn classify-char
  [c]
  (cond
    (is-number? c) [:number c]
    (= c indeterminate) [:indeterminate]

    (= c \+) [:plus]
    (= c \-) [:minus]
    (= c \/) [:slash]
    (= c \*) [:star]
    (= c \=) [:equals]

    (= c \space) []

    :else [:unknown]))

(defn generate-tokens
  [state c]
  (let [token (classify-char c)]
    (update state :tokens #(conj % token))))

(defn tokenize
  [equation]
  (let [equation (seq equation)]
    (->> equation
         (reduce generate-tokens {:tokens []})
         (println))))
