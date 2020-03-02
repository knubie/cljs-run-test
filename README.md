# (run-test)

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.knubie/cljs-run-test.svg)](https://clojars.org/org.clojars.knubie/cljs-run-test)

This library provides a macro (run-test) that you can use to run a single test with fixtures in clojurescript.

## Usage

```cljs
(ns my.project.test
  (:require [cljs-run-test :refer [run-test]
            [cljs.test     :refer [deftest is use-fixtures]]))  

(use-fixtures :each
  {:before #(println "Before")}
  {:after  #(println "After")})

(deftest one-equals-one
  (println "Running one-equals-one")
  (is (= 1 1)))

(run-test one-equals-one)
```
```
Before
Running one-equals-one
After
Ran 1 tests containing 1 assertions.
0 failures, 0 errors.
```

Or provide a fully qualified symbol from another namespace.

```cljs
(ns my.project.other-ns
  (:require [cljs-run-test :refer [run-test]))

(run-test my.project.test/one-equals-one)
```
