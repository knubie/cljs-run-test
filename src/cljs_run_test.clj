(ns cljs-run-test)

(defmacro run-test
  "Takes a test function and runs it; prints results."
  [test-symbol]
  
  (let [ns-str (or (namespace test-symbol)
                   ;; Current namespace
                   (str (:name (:ns &env))))]
    (list 'cljs-run-test/run-test-fn
          {:var `(var ~test-symbol)

           :once-fixtures
           (when (cljs.analyzer.api/ns-resolve
                   (symbol ns-str) 'cljs-test-once-fixtures)
             (symbol ns-str "cljs-test-once-fixtures"))

           :each-fixtures
           (when (cljs.analyzer.api/ns-resolve
                   (symbol ns-str) 'cljs-test-each-fixtures)
             (symbol ns-str "cljs-test-each-fixtures"))})))
