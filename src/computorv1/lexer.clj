(ns computorv1.lexer)

(defn emit-token
  [equation-str]
  (or
    (when-let [match (re-find #"^\d+" equation-str)]
      [{:type :number
        :value (parse-long match)}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\+" equation-str)]
      [{:type :plus}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\-" equation-str)]
      [{:type :minus}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\/" equation-str)]
      [{:type :slash}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\*" equation-str)]
      [{:type :star}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\=" equation-str)]
      [{:type :equals}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\^" equation-str)]
      [{:type :caret}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^X" equation-str)]
      [{:type :indeterminate}
       (.substring equation-str (count match))])

    (when-let [match (re-find #"^\ +" equation-str)]
      [nil
       (.substring equation-str (count match))])

    (when (seq equation-str)
      [{:type :unknown}
       (.substring equation-str 1)])))

(defn cons-if-not-empty
  [token s]
  (if-not (empty? token)
    (cons token s)
    s))

(defn unfold-equation
  [equation-str]
  (if-let [[token new-equation-str] (emit-token equation-str)]
    (cons-if-not-empty token (lazy-seq (unfold-equation new-equation-str)))
    nil))

(defn tokenize
  [equation]
  (-> equation
       (unfold-equation)))

