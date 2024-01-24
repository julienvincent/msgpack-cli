clean:
  clojure -T:build clean

build: clean
  clojure -T:build build

native-image:
  $GRAALVM_HOME/bin/native-image -jar target/msgpack-cli.jar target/msgpack

build-native: build native-image

install: build-native
  cp target/msgpack ~/.bin/msgpack

run *args: 
  clojure -M -m k16.kl.cli {{args}}
