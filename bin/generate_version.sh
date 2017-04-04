#!/bin/bash

readonly SOURCE_FOLDER=$(dirname $(readlink -f ${0}))
readonly VERSION=$(git describe --tags --always --dirty)

if [[ "$#" -eq 0 ]]; then
  version_folder=${SOURCE_FOLDER}/../build/resources/main
else
  version_folder=$1
fi

echo "version is ${VERSION}"

mkdir -p ${version_folder}
echo ${VERSION} > ${version_folder}/VERSION