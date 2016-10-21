#!/usr/bin/env bash

version=$(git describe --tags --always --dirty)

echo "version is ${version}"

echo ${version} > ./src/main/resources/VERSION