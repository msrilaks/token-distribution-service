# token-distribution-service
This is a REST service that allows generation of tokens according to a specified distribution and returns tokens to incoming GET requests with adherence to the distribution percentages.

Build Service:
gradlew build
docker build -t msrilaks/token-distribution-service .

Run Docker Container:
docker network create my-net
docker run -p 6379:6379 --net my-net --name redis -d redis
docker run -p 8080:8080 --net my-net msrilaks/token-distribution-service

Run Using Docker Compose:
docker-compose up

Swagger UI:
http://localhost:8080/swagger-ui.html

docker exec -it my-redis redis-cli -h 127.0.0.1 -p 6379
