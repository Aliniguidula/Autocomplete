**Autocomplete** 🔍

Efficient Trie-based Autocomplete System with Java

This project implements an autocomplete system using a Trie (prefix tree) data structure in Java. Given a collection of query strings paired with weights (indicating their popularity or relevance), the system returns all queries that begin with a given prefix, sorted in descending order of weight.

Autocomplete functionality is a core feature in modern applications like search engines, messaging apps, and digital assistants. This project demonstrates how to build such a system from the ground up with attention to performance, modular design, and testing.

✨ **Features**
- Trie-based autocomplete: Efficient insertion and lookup for fast prefix matching.
- Weighted suggestions: Returns results sorted by descending weight.
- Case-insensitive search: Queries are normalized to lowercase.
- Encapsulation & safety: Immutable Term results and error handling for edge cases.
- Unit tested: Includes robust testing for core components using JUnit.
- File I/O support: Loads terms and weights from external files.

🧠 **Core Concepts**
- Data Structures: Trie, recursive tree traversal.
- Object-Oriented Design: Interfaces (ITerm, IAutocomplete), encapsulated classes (Term, Node, Autocomplete).
- Custom Comparators: Lexicographic and weight-based sorting for results.
- Algorithm Design: Efficient prefix search and top-match retrieval.
- UML & Domain Modeling: Includes class diagrams and clear software architecture.
- Testing: JUnit-based test coverage for all components.
