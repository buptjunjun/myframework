#!/bin/bash
JAVA_PATH="${{java.path}}"
JETTY_HOME="${{JETTY_HOME}}"

PROJ_PATH="${{project.dir}}"
CLASS_DIR="${PROJ_PATH}/WebRoot/WEB-INF/classes"
JAR_PATH="${{jar.path}}"
LIB_DIR="${PROJ_PATH}/lib/"
LIB_DIR="${JETTY_HOME}/lib/"
CP="${CLASS_DIR}:${JETTY_HOME}/start.jar"
MAIN="${{main-class}}"
LOG_DIR="${PROJ_PATH}/logs"

OPT="-Dfile.encoding=UTF-8"
OPT="$OPT -Djetty.home=${JETTY_HOME}"
OPT="$OPT -Dlog.dir=${LOG_DIR}"
OPT="$OPT -Djetty.port=${{server.port}}"
OPT="$OPT -DSTOP.PORT=${{stop.port}}"
OPT="$OPT -DSTOP.KEY=${{stop.key}}"
OPT="$OPT -Dcontext.path=${{context.path}}"

#OPT="$OPT -Dcom.sun.management.jmxremote"
#OPT="$OPT -Dcom.sun.management.jmxremote.port=12345"
#OPT="$OPT -Dcom.sun.management.jmxremote.authenticate=false"
#OPT="$OPT -Dcom.sun.management.jmxremote.ssl=false"

for i in `ls ${LIB_DIR}`; do
        CP="${CP}"":""${LIB_DIR}${i}";
done

PROGRAM_ARG="dir=${PROJ_PATH}"

cd ${PROJ_PATH}

mkdir -p ${LOG_DIR} 2>/dev/null

${JAVA_PATH} ${OPT} -classpath ${CP} -jar ${JETTY_HOME}/start.jar --ini=jetty/start.ini </dev/null >>${LOG_DIR}/jetty.log 2>>${LOG_DIR}/jetty.log.err 
