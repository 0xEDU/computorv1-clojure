(ns computorv1.lexer)

(def decimal-base 10)
(def indeterminate \X)

(defn is-number?
  [c]
  (not (== (Character/digit c decimal-base) -1)))

(defn char-to-number
  [c]
  (- (int c) 48))

(defn classify-char
  [c]
  (cond
    (is-number? c) {:type :number
                    :value (char-to-number c)}
    (= c indeterminate) {:type :indeterminate}

    (= c \+) {:type :plus}
    (= c \-) {:type :minus}
    (= c \/) {:type :slash}
    (= c \*) {:type :star}
    (= c \=) {:type :equals}
    (= c \^) {:type :caret}

    (= c \space) nil

    :else {:type :unknown}))

(defn conj-if-not-empty
  [tokens token]
  (if (empty? token)
    tokens
    (conj tokens token)))

(defn generate-tokens
  [state c]
  (let [token (classify-char c)]
    (update state :tokens #(conj-if-not-empty % token))))

(defn tokenize
  [equation]
  (let [equation (seq equation)]
    (->> equation
         (reduce generate-tokens {:tokens []}))))


