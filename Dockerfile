FROM openjdk:21
COPY . ./application
WORKDIR /application
CMD [ "javac", "src/Main.java" ]

