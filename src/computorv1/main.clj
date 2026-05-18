(ns computorv1.main
  (:require [computorv1.lexer :as lexer]))

(defn -main
  [equation]
  (-> equation
      (lexer/tokenize)))
