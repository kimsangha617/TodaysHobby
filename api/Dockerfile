FROM openjdk:17
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/api-0.0.1.jar api.jar
#도커에 들어가 실제로 실행할 명령어
ENTRYPOINT ["java", "-jar", "/deploy/api.jar"]

