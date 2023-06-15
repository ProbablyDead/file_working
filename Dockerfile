FROM openjdk:21
ADD . ./application
WORKDIR /application
CMD [ "javac", "src/Main.java" ]

