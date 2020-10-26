FROM openjdk:8-jdk-alpine
ARG BUILD_VERSION

ENV DB_HOST localhost
ENV DB_PORT 3306

ADD dropwizard-webapp-${BUILD_VERSION}.jar dropwizard-webapp.jar
ADD application.yml application.yml 
ADD docker-entrypoint.sh docker-entrypoint.sh
RUN chmod a+x /docker-entrypoint.sh

EXPOSE 8080 8081

ENTRYPOINT ["/docker-entrypoint.sh"]
