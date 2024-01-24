(ns julienvincent.msgpack-cli.cli
  (:require
   [clojure.java.io :as io]
   [jsonista.core :as json]
   [msgpack.core :as msgpack])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn- decode-and-print [^String line]
  (->> (.getBytes line)
       (.decode (java.util.Base64/getDecoder))
       msgpack/unpack
       json/write-value-as-string
       println))

(defn -main [& _]
  (->> (System/in)
       io/reader
       line-seq
       (map decode-and-print)
       doall))
