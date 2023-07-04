FROM adoptopenjdk/openjdk11:jre-11.0.19_7-ubuntu
ADD target/eca-configserver-*.jar ecaconfigserver.jar
ENV JAVA_OPTS=""
ENV APP_NAME="ecaconfigserver"
ENV SECURITY_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENV MEMORY_OPTS=" -XX:+UseG1GC \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:MaxMetaspaceSize=256m \
-XX:HeapDumpPath=/tmp/Heapdump.hprof \
-XX:+UseStringDeduplication"

CMD java -jar /ecaconfigserver.jar