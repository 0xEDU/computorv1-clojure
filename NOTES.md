# Notes

## Parser

### Definitions

The syntax is roughly:

```
equation    ::= expression "=" expression
expression  ::= term (("+" | "-") term)*
term        ::= factor (("*" | "/") factor)*
factor      ::= primary ("^" factor)?
primary     ::= number
                | indeterminate
```

The selected approach for parsing is creating parser combinators. Like the tokenizer, small parsers functions will be composed to parse everything.

### Thoughts
- This is transformation step, a pure function is adequate as a token vector will be converted to a ???

### Questions

- Q: Should I type the tree? Is typing helpful here?
- Q: What is the concrete implementation of an AST? It's inherently recursive
- Q: Is an AST the most adequate DS for this problem? A: Not necessarily, but I want to build one just for learning purpouses
- Q: Where should I add a validation step?
