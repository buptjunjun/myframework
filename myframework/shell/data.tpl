#!/bin/bash
JAVA_PATH="${{java.path}}"
OPT='-Dfile.encoding=UTF-8'
#OPT="$OPT -Xmx2g"
#OPT="$OPT -Dcom.sun.management.jmxremote"
#OPT="$OPT -Dcom.sun.management.jmxremote.port=12346"
#OPT="$OPT -Dcom.sun.management.jmxremote.authenticate=false"
#OPT="$OPT -Dcom.sun.management.jmxremote.ssl=false"
PROJ_PATH="${{project.dir}}"
CLASS_DIR="${PROJ_PATH}/bin/"
JAR_PATH="${{jar.path}}"
LIB_DIR="${PROJ_PATH}/lib/"
CP="${CLASS_DIR}"
LOG_PATH="${PROJ_PATH}/logs"
MAIN="${{main-class}}"

for i in `ls ${LIB_DIR}`; do
        CP="${CP}"":""${LIB_DIR}${i}";
done

PROGRAM_ARG="dir=${PROJ_PATH}"

cd ${PROJ_PATH}

mkdir ${LOG_PATH} 2>/dev/null

${JAVA_PATH} ${OPT} -classpath ${CP} ${MAIN} ${PROGRAM_ARG} </dev/null >out 2>err
