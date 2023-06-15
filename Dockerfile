FROM openjdk:21
WORKDIR /application
ADD . ./application
CMD [ "javac", "src/Main.java" ]

