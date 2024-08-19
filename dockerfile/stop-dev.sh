docker-compose -f docker-compose.db.yaml down -v
docker-compose -f docker-compose.kafka.yaml down -v

// docker stop kafka zookeeper kafka-ui