#!/bin/bash

readonly SOURCE_FOLDER=$(dirname $(readlink -f ${0}))
readonly APP_FOLDER=${SOURCE_FOLDER}/..
readonly LOG_FOLER=${APP_FOLDER}/logs
readonly GC_LOG_FOLER=${LOG_FOLER}/gc

if [[ ! -d "${GC_LOG_FOLER}" ]]; then
  mkdir -p "${GC_LOG_FOLER}"
fi

readonly MONGO_HOST="142.133.51.125"

MONGODB_PORT_27017_TCP_ADDR=${MONGO_HOST} java \
  -Xms1g -Xmx1g -XX:MetaspaceSize=128m \
  -XX:+UseG1GC \
  -XX:InitiatingHeapOccupancyPercent=15 \
  -XX:MaxGCPauseMillis=200 \
  -XX:+ParallelRefProcEnabled \
  -XX:SoftRefLRUPolicyMSPerMB=0 \
  -XX:+PrintGCDetails \
  -XX:+PrintGCDateStamps \
  -XX:+PrintTenuringDistribution \
  -XX:+PrintAdaptiveSizePolicy \
  -XX:+PrintReferenceGC \
  -XX:+UnlockDiagnosticVMOptions \
  -XX:+G1SummarizeRSetStats \
  "-Xloggc:${GC_LOG_FOLER}/jvm_gc-%t.log" \
  -XX:+UseGCLogFileRotation \
  -XX:GCLogFileSize=40M \
  -XX:NumberOfGCLogFiles=20 \
  -jar "${APP_FOLDER}/build/libs/calculator-service.war"