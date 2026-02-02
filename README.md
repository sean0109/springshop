#### 프로젝트 개요

Spring Boot 기반의 간단한 회원등록 - 상품 주문 CRUD 프로젝트 입니다.

목적은 **Docker 컨테이너 기반 배포**, **클라우드 환경 구축**, **CI/CD 자동화**까지 전체 개발 파이프라인을 경험하고자 시작한 프로젝트입니다.

#### 기술 스택

```
Backend:    Java 17, Spring Boot 3.5.6, Spring Data JPA, MyBatis
Frontend:   Thymeleaf, JavaScript, HTML5/CSS (반응형 웹)
Database:   Oracle Database (운영), H2 Database (개발)
DevOps:     Docker, Docker Compose, AWS EC2
CI/CD:      GitHub Actions (구축 예정)
Tools:      Gradle, Lombok, P6Spy
```


#### 아키텍처

**로컬 개발 환경 - Docker 멀티 컨테이너 구성**

```
┌─────────────────────────────────────────────────────────┐
│                    Docker Compose                       │
│                                                         │
│  ┌────────────────────┐      ┌─────────────────────┐    │
│  │  springshop-app    │      │  springshop-oracle  │    │
│  │  (Spring Boot)     │◄────►│  (Oracle DB)        │    │
│  │                    │      │                     │    │
│  │  Port: 8090        │      │  Port: 1521         │    │
│  └────────────────────┘      └─────────────────────┘    │
│           ▲                           ▲                 │
│           │                           │                 │
│           └───── bridge network ──────┘                 │
│                (springshop-network)                     │
└─────────────────────────────────────────────────────────┘
```

**AWS 클라우드 배포 환경 (구축 예정)**

```
┌─────────────────────────────────────────────────────────┐
│                      GitHub                             │
│                                                         │
│      ┌──────────────────────────────┐                   │
│      │   GitHub Actions (CI/CD)     │                   │
│      │  - Build & Test              │                   │
│      │  - Docker Image Build        │                   │
│      │  - Deploy to EC2             │                   │
│      └──────────────┬───────────────┘                   │
└─────────────────────┼───────────────────────────────────┘
                      │
                      ▼
         ┌────────────────────────┐
         │      AWS EC2           │
         │                        │
         │  ┌──────────────────┐  │
         │  │  Docker Engine   │  │
         │  │                  │  │
         │  │  - Spring App    │  │
         │  │  - Oracle DB     │  │
         │  └──────────────────┘  │
         └────────────────────────┘
```
