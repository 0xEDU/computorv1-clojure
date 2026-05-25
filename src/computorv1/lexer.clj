(ns computorv1.lexer
  (:require [clojure.string :as str]))

(def token-specs
  [[#"^\d+"
    (fn [match]
      {:type :number
          :value (parse-long match)})]

   [#"^\+"
    (fn [_]
      {:type :plus})]
   
   [#"^\-"
    (fn [_]
      {:type :minus})]

   [#"^\/"
    (fn [_]
      {:type :slash})]
   
   [#"^\*"
    (fn [_]
      {:type :star})]

   [#"^\="
    (fn [_]
      {:type :equals})]

   [#"^\^"
    (fn [_]
      {:type :caret})]

   [#"^X"
    (fn [_]
      {:type :indeterminate})]])

(defn matching-spec
  [equation-str]
  (some (fn [[regex build-token]]
          (when-let [match (re-find regex equation-str)]
            [match build-token]))
        token-specs))

(defn emit-token
  [equation-str]
  (let [equation-str (str/trim equation-str)]
    (if-let [[match build-token] (matching-spec equation-str)]
      [(build-token match)
       (.substring equation-str (count match))]

      (when (seq equation-str)
        [{:type :unknown}
         (.substring equation-str 1)]))))

(defn token-seq
  [equation-str]
  (if-let [[token new-equation-str] (emit-token equation-str)]
    (cons token (lazy-seq (token-seq new-equation-str)))
    nil))

(defn tokenize
  [equation]
  (token-seq equation))

