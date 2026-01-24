
# 빌드
FROM gradle:8.14.3-jdk17 AS build
# 컨테이너 안에서 작업할 폴더 /app 으로 설정
WORKDIR /app

# Gradle 파일 먼저 복사 (gradle 실행 설정 파일 복사)
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./

# 의존성 라이브러리 빌드 ( 위 에서 copy 시 기존 파일과 해시값 비교, 변경시에만 재실행)
RUN ./gradlew dependencies --no-daemon || true

# 소스 코드 복사
COPY src src

# 소스 코드 jar 파일로 빌드 ( 테스트 제외 ), 테스트 건너 뛰기
RUN ./gradlew clean build -x test --no-daemon

# Runtime (실행단계)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 보안 : non-root 유저 생성 ( 유저는 도커 이미지가 사라질 때 사라진다. )
# 도커
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# 빌드 된 JAR 파일을 복사 (/app/ 경로에 생성)
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 포트 정보
EXPOSE 8090

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

