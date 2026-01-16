# Build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app 

# Gradle Wrapper 복사
COPY gradlew ./
COPY gradle gradle

# 실행 권한 부여
RUN chmod +x gradlew

# Gradle 설정 파일
COPY build.gradle settings.gradle ./

# 의존성 다운로드
RUN ./gradlew dependencies --no-daemon

# 소스
COPY src src
RUN ./gradlew build -x text --no-daemon

#jdk 17 기반의 이미지 사용
FROM eclipse-temurin:17-jdk-alpine

#작업 디렉토리 설정
WORKDIR /app

#빌드된 jar 파일 복사
COPY --from=build build/libs/*-0.0.1-SNAPSHOT.war app.war

#PORT 열기
EXPOSE 8080

#실행
ENTRYPOINT ["java","-jar","app.war"]