(ns parser-test
  (:require [clojure.test :as t]
            [computorv1.parser :as parser]))

(t/deftest parser-test
  (t/testing "basic parsing"
    (t/is nil?
          (parser/parse "dale"))))
