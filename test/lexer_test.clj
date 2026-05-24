(ns lexer-test
  (:require [clojure.test :as t]
            [computorv1.lexer :as lexer]))

(t/deftest lexer-test
  (t/testing "parsing a basic equation"
    (t/is (= [{:type :number :value 5}
              {:type :plus}
              {:type :number :value 4}
              {:type :star}
              {:type :indeterminate}
              {:type :plus}
              {:type :indeterminate}
              {:type :caret}
              {:type :number :value 2}
              {:type :equals}
              {:type :indeterminate}
              {:type :caret}
              {:type :number :value 2}]
             (lexer/tokenize "5 + 4 * X + X^2= X^2")))

  (t/testing "parsing an equation with more numbers"
    (t/is (= [{:type :number :value 15}
               {:type :minus}
               {:type :number :value 42}
               {:type :star}
               {:type :indeterminate}
               {:type :plus}
               {:type :indeterminate}
               {:type :caret}
               {:type :number :value 2}
               {:type :equals}
               {:type :number :value 35}
               {:type :slash}
               {:type :indeterminate}
               {:type :caret}
               {:type :number :value 2}]
             (lexer/tokenize "15 - 42 * X + X^2= 35 / X^2"))))))

(t/run-tests 'lexer-test)
