(ns cljs-run-test
  (:require [cljs.test :as t]
            [cljs.analyzer.api])
  (:require-macros [cljs-run-test]))


(defn- report-block []
  (t/do-report
    (assoc (:report-counters (t/get-current-env))
           :type :summary))
  (t/clear-env!))


(defn run-test-fn
  "Groups vars by their namespace and runs test-vars on them with
  appropriate fixtures assuming they are present in the current
  testing environment."
  [{:keys [var once-fixtures each-fixtures]}]

  ;; Collect fixtures
  (let [ns (:ns (meta var))]
    (when once-fixtures
      (t/update-current-env! [:once-fixtures] assoc ns once-fixtures))
    (when each-fixtures
      (t/update-current-env! [:each-fixtures] assoc ns each-fixtures)))

  ;; Run the test
  (t/run-block (concat (t/test-vars-block [var])
                       [report-block])))
