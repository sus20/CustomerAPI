docker-compose -f docker-compose.db.yaml up -d
docker-compose -f docker-compose.kafka.yaml up -d


// docker-compose --env-file .env.stg up --build -d
//docker-compose --env-file .env.stg down
