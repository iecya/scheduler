#!/usr/bin/env bash

if [[ $# -eq 0 ]]
then
echo "Please provide a time parameter in the format HH:MM"
fi

BIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"


/usr/bin/java -jar $BIN_DIR/../target/uberjar/scheduler-0.1.0-standalone.jar "$1"