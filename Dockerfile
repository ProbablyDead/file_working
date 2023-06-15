FROM openjdk:8-jdk-alpine
COPY . ./application
WORKDIR /application
CMD ["la"]

