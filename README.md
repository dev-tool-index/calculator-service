# calculator-service

[![Build Status](https://travis-ci.org/dev-tool-index/calculator-service.svg?branch=master)](https://travis-ci.org/dev-tool-index/calculator-service)
[![Coverage Status](https://coveralls.io/repos/github/dev-tool-index/calculator-service/badge.svg?branch=master)](https://coveralls.io/github/dev-tool-index/calculator-service?branch=master)

The project is a part of __Example Application__ in [Developer Tool Index](https://www.gitbook.com/book/dev-tool-index/developer-tool-index/).

## build
> $ ./gradlew clean build

## run app
> $ java -jar build/libs/calculator-service-<version>.jar

## run unit tests
> $ ./gradlew check

## simple manual test
```{r, engine='bash' curl}
$ curl http://localhost:8080/calc/add?arg1=3&arg2=2
{"arg1":3,"arg2":0,"op":"add","result":3}
```
