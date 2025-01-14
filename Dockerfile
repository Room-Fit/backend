# Base image (OpenJDK 21을 사용)
FROM openjdk:21-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# SNAPSHOT.jar로 끝나는 .jar 파일만 복사
COPY build/libs/*SNAPSHOT.jar /app/roomfit-server.jar

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/roomfit-server.jar"]