#!/bin/bash

readonly SOURCE_FOLDER=$(dirname $(readlink -f ${0}))
readonly VERSION=$(git describe --tags --always --dirty)

if [[ "$#" -eq 0 ]]; then
  version_file=${SOURCE_FOLDER}/../build/resources/main/VERSION
else
  version_file=$1
fi

echo "version is ${VERSION}"

echo ${VERSION} > ${version_file}