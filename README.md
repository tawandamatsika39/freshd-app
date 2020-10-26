# FreshD Application


## Build

- build with Maven

      mvn clean package docker:build

- or, build with Docker

      docker image build \
        --build-arg BUILD_VERSION=0.0.1-SNAPSHOT \
        -f Dockerfile.build \
        -t freshd-app:latest \
        -t freshd-app:0.0.1-SNAPSHOT .

## Run

- run MySQL container:

      docker run --rm -d \
        --name mysql \
        -p 3000:3306 \
        -e MYSQL_ROOT_PASSWORD='p$ssw0rd' \
        -e MYSQL_DATABASE=my_app_db \
        -e MYSQL_ROOT_HOST=% \
        mysql:8.0.2

- run Dropwizard application container

      docker run -d --rm \
        --name freshd-app \
        -p 8080:8080 \
        -p 8081:8081 \
        -e DB_HOST=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' mysql` \
        freshd-app:0.0.1-SNAPSHOT


- check the Dropwizard server is up and running

      curl http://localhost:8081/healthcheck

- invoke REST(ful) APIs using `curl` commands
  - create new task

        curl -X POST http://localhost:8080/tasks \
           -d '[{"title": "Task #1", "description": "Sample Task"}]' \
           -H "Content-Type: application/json"

  - list all tasks

        curl http://localhost:8080/tasks

  - get task by its identifier

        curl http://localhost:8080/tasks/1

  - delete task by its identifier

        curl -X DELETE http://localhost:8080/tasks/1
