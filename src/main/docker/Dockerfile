FROM openjdk:8-jdk-alpine
ARG BUILD_VERSION

ENV DB_HOST localhost
ENV DB_PORT 3306

ADD freshd-service-${BUILD_VERSION}.jar freshd-service.jar
ADD application.yml application.yml 
ADD docker-entrypoint.sh docker-entrypoint.sh
RUN chmod a+x /docker-entrypoint.sh

EXPOSE 8080 8081

ENTRYPOINT ["/docker-entrypoint.sh"]
