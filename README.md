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
  - create new product

        curl --location --request POST 'http://localhost:8080/products/' \
            --header 'Content-Type: application/json' \
            --data-raw '{
	            "id": "",
	            "name": "Corn",
	            "category": "Vegetables",
	            "description": "Fresh Corn from the farm ",
	            "availability": "IN_STOCK",
	            "price": 98.50,
	            "quantity": 23,
	            "supplierId": "fa5fed1a-8d4c-11ea-bc55-0242ac130003"
            }'

  - get product by its identifier

        curl --location --request GET 'http://localhost:8080/products/36d6957f-c67b-4466-8542-0bb0dee52370' \
        --header 'Content-Type: application/json' \
        --data-raw ''
