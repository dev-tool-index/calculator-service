# calculator-service

The project is a part of __Example Application__ in [Developer Tool Index](https://www.gitbook.com/book/dev-tool-index/developer-tool-index/).

## build
> $ ./gradlew clean build

## run app
> $ java -jar build/libs/calculator-service-<version>.jar

## run unit tests
> $ ./gradlew check

## simple manual test
> $ curl http://localhost:8080/calc/add?arg1=3&arg2=2
> {"arg1":3,"arg2":0,"op":"add","result":3}

