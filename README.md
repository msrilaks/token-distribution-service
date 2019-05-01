# token-distribution-service [![Build Status](https://travis-ci.org/msrilaks/token-distribution-service.svg?branch=master)](https://travis-ci.org/msrilaks/token-distribution-service)
Token Distribution Service is a dockerized micro service which exposes REST
API's to create tokens based on a specified percentage distribution of tokens.
For incoming GET requests made to fetch a token for a distribution, it
returns  token such that the distribution percentages are adhered to any point in time.
Token Distribution Service can scale horizontally.

# LinkedIn Article with More Details
https://www.linkedin.com/pulse/scalable-percentage-based-entity-distribution-cloud-using-mudaliar/

# How to Build Service and Docker Image
* gradlew build
* docker build -t msrilaks/token-distribution-service .

# How to Run Services

## Clean up all containers, use if necessary:
docker system prune -a --volumes

## Run Using Docker Compose
docker-compose up

## Or Run Docker Containers
* docker network create my-net
* docker run -p 6389:6379 --net my-net --name redis -d redis
* docker run -p 8080:8080 -e REDIS_HOST=redis -e REDIS_PORT=6389 -e LOG_DIR=logs
 --net my-net msrilaks/token-distribution-service

# Swagger UI:
http://localhost:8080/swagger-ui.html