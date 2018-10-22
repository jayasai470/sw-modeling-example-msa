# 기반 이미지
FROM openjdk:8u111-jdk-alpine

# 볼륨 연결
VOLUME /tmp

# 애플리케이션 복사
ADD /target/*-exec.jar app.jar

# 포트노출
EXPOSE 8080

# 자바 실행
ENTRYPOINT ["java","-Xmx256M","-jar","/app.jar"]
