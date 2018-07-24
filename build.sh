cd Exam1
mvn clean package
mvn assembly:assembly
cd ..

docker build -t mysql:test ./docker/mysql/
docker build -t jar:test .