## token-distribution-service
This is a RESTful microservice that allows generation of tokens according to a
specified distribution and returns tokens to incoming GET requests with adherence to the distribution percentages.

## Explanation
Scalable Percentage Based Distribution in Cloud using Redis Circular List

Consider a use case in the cloud where you want to distribute objects to
users such that each user request gets him one object in adherence to a
percentage distribution preset for the objects.
The objects could itself represent anything. It could be a JSON representation
of a view which would imply that a certain percentage of users will see a
specific view in response to their request. This is a typical A/B testing
usecase.
The object could represent a product advertisement on your website so
that a certain percentage of users visiting your website will see the advertisement
for product A and another percentage of users will see the advertisement for
Product B.
The object could also represent node instances running a microservice and the
use case may be a load distribution over these instances.
The applications are many for a scalable percentage based distribution of
entities.

This article describes how Redis Circular Lists can be used to build a
horizontally scalable microservice (https://github.com/msrilaks/token-distribution-service) to address the
above usecases. In this example, the object is a string token.

Redis
Redis is an open source, in-memory data structure store, used as a database, cache and message broker.
Redis can also be setup for scalability and replication, topics that will not be
covered in this article.

Redis Circular List
Redis supports Lists data type which is essentially a collections of string
elements sorted according to the order of insertion. They are basically linked
lists.
Among the several commands supported on lists, redis has an interesting command
called rpoplpush. The usage of this command is:
RPOPLPUSH source destination
where source and destination are each a Redis list.
The command atomically returns and removes the last element (tail) of the list
stored at source, and pushes the element at the first element (head) of the list
 stored at destination.
Interestingly when this command is run with the same source and destination
lists, it effectively works as a circular list and allows atomic traversal over
the list by multiple concurrent worker threads such that each worker thread
reads the next item in the list.
https://redis.io/commands/rpoplpush
The Redis Circular List is at the heart of our solution.

token-distribution-service
https://github.com/msrilaks/token-distribution-service is a dockerized
microservice exposes REST API's to create distributions of tokens based on
percentages. When a GET request is made to fetch a token for a distribution, it
would return a token such that the distribution percentages are adhered to any
point in time.
Let me explain this with a simple example below:
Lets say you have a need for a cloud native service for
some content distribution based on percentages to incoming user requests. In
this example, I will trivialize the content to just a colour.

An admin sets the percentage distributions of the colours
to be as follows:
POST: /distributions
{
	"name": "colour-distribution",
	"groups": [{
			"token": "red",
			"percentage": 20
		},
		{
			"token": "blue",
			"percentage": 30
		},
		{
			"token": "green",
			"percentage": 50
		}
	]
}
The response body will contain a newly generate id for this distribution:
{
    "id": "c4cef4c9-e1ae-43e9-9860-0dc45458cc2a"
}

Subsequently, for every 10 incoming GET requests made for this
distribution , 30% as blue and 50% will see green.
GET /distributions/c4cef4c9-e1ae-43e9-9860-0dc45458cc2a/token
20% will get the reponse token as red:
{
    "token": "red"
}
30% will get the reponse token as red:
{
    "token": "blue"
}
50% will get the reponse token as red:
{
    "token": "green"
}

Horizontal scaling:
The token-distribution-service can horizontally scale. As the traffic grows,
additional containers can be started. The docker-compose.yaml starts two
containers for this service. Scaling redis is not covered here.

##Usage

##Build Service:
gradlew build
docker build -t msrilaks/token-distribution-service .

##Run Services:

#Clean up all containers, use if necessary:
docker system prune -a --volumes

#Run Using Docker Compose:
docker-compose up

#Or Run Docker Containers:
docker network create my-net
docker run -p 6389:6379 --net my-net --name redis -d redis
docker run -p 8080:8080 -e REDIS_HOST=redis -e REDIS_PORT=6389 -e LOG_DIR=logs --net my-net msrilaks/token-distribution-service

##Swagger UI:
http://localhost:8080/swagger-ui.html

#docker exec -it my-redis redis-cli -h 127.0.0.1 -p 6379
#https://redislabs.com/ebook/part-3-next-steps/chapter-10-scaling-redis/