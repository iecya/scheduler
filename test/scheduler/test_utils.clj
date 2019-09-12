(ns scheduler.test-utils
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [scheduler.core :as sc]))

(defn get-input-files
  []
  (map #(.getName %) (.listFiles (io/file "resources/inputs"))))

(defn- output-file
  [file]
  (string/replace file #"input" "output"))


(defn test-file
  [file]
  (testing (str "Testing file " file)
    (let [[time & lines] (->> (str "inputs/" file) io/resource io/reader line-seq)
          output-lines (->> (str "outputs/" (output-file file)) io/resource io/reader line-seq vec)]
      (doseq [line lines]
        (let [line-idx (.indexOf lines line)
              result (sc/process-line time line)
              output (nth output-lines line-idx)]
          (is (= (nth output-lines line-idx) result))
          (println "Output: " output)
          (println "Result: " result)
          #_(when-not (= (nth output-lines line-idx) result)
              (println "Error line at index: " line-idx " - line: " line)))))))
