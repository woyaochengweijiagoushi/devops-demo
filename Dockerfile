FROM openjdk:17

COPY target/*.jar  /app.jar

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080

#指定镜像启动命令
ENTRYPOINT ["java","-jar","/app.jar"]