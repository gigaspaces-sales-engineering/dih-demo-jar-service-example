#Build Docker image

Use java 11

mvn clean package spring-boot:repackage

docker build -t service-name .
