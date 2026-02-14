# SpringShop 프로젝트 TODO

## 최종 목표
Docker 멀티 컨테이너로 구성한 로컬 환경의 Spring Boot 애플리케이션을 **AWS EC2 서버에 배포**하고, **GitHub Actions를 통한 CI/CD 자동화** 구축

---

## 현재 상태
- ✅ Spring Boot 3.5.6 + MyBatis + Oracle DB 애플리케이션 개발 완료
- ✅ Docker Compose로 멀티 컨테이너 환경 구성 (Oracle DB + Spring Boot App)
- ✅ Dockerfile 작성 완료 (Multi-stage build)
- 🔄 CI/CD 파이프라인 구축 중 (Phase 1 진행 중)
- ❌ AWS 배포 환경 없음

---

## Phase 1: GitHub Actions CI/CD 구축

### 1.1 기본 CI 워크플로우 설정
- [x] `.github/workflows/` 디렉토리 생성
- [x] `pr-validation.yml` 작성 (PR 검증 워크플로우)
  - Gradle 빌드
  - 테스트 실행 (H2 in-memory DB)
  - 빌드 결과 확인
- [ ] `main-cicd.yml` 작성 (Main 브랜치 CI/CD) ⬅️ **다음 단계**
  - Gradle 빌드 & 테스트
  - Docker 이미지 빌드
  - Docker 레지스트리 푸시 (GHCR or Docker Hub)

### 1.2 코드 품질 관리 (선택사항)
- [ ] `build.gradle`에 JaCoCo 플러그인 추가
- [ ] 테스트 커버리지 리포트 자동 생성
- [ ] PR에 커버리지 코멘트 자동 추가

### 1.3 의존성 관리 (선택사항)
- [ ] `.github/dependabot.yml` 작성
- [ ] Gradle 의존성 자동 업데이트 설정
- [ ] GitHub Actions 버전 자동 업데이트 설정

### 1.4 보안 스캔 (선택사항)
- [ ] Trivy를 이용한 Docker 이미지 취약점 스캔
- [ ] GitHub Security 탭에 결과 자동 업로드

---

## Phase 2: AWS EC2 배포 환경 구축

### 2.1 AWS 인프라 준비
- [ ] AWS EC2 인스턴스 생성
  - OS: Ubuntu 22.04 LTS (권장)
  - 인스턴스 타입: t3.medium 이상 (Docker + Oracle DB 고려)
  - 보안 그룹 설정:
    - SSH (22)
    - HTTP (80)
    - HTTPS (443)
    - Application (8090)
    - Oracle DB (1521) - 필요시
- [ ] Elastic IP 할당 (고정 IP)
- [ ] Key Pair 생성 및 로컬에 저장

### 2.2 EC2 서버 초기 설정
- [ ] SSH 접속 확인
- [ ] Docker 설치
  ```bash
  sudo apt update
  sudo apt install docker.io docker-compose -y
  sudo usermod -aG docker $USER
  ```
- [ ] Docker Compose 설치
- [ ] Git 설치 및 리포지토리 클론

### 2.3 환경 변수 및 시크릿 관리
- [ ] EC2에 `.env` 파일 생성 (데이터베이스 credential 등)
- [ ] GitHub Secrets 등록:
  - `EC2_HOST`: EC2 퍼블릭 IP
  - `EC2_USER`: SSH 사용자명 (ubuntu)
  - `EC2_SSH_KEY`: EC2 Private Key
  - `DOCKER_USERNAME`: Docker Hub 사용자명 (선택)
  - `DOCKER_PASSWORD`: Docker Hub 토큰 (선택)

### 2.4 데이터베이스 설정
- [ ] EC2에서 Oracle DB 컨테이너 실행 또는
- [ ] AWS RDS for Oracle 사용 고려 (프로덕션 권장)
- [ ] 데이터베이스 초기 스키마 및 데이터 마이그레이션

---

## Phase 3: CD (Continuous Deployment) 자동화

### 3.1 배포 스크립트 작성
- [ ] `deploy.sh` 스크립트 작성 (EC2에서 실행)
  ```bash
  # Docker 이미지 pull
  # 기존 컨테이너 중지
  # 새 컨테이너 실행
  # 헬스체크
  ```

### 3.2 GitHub Actions 배포 워크플로우
- [ ] `deploy-to-ec2.yml` 작성
  - Main 브랜치 푸시 시 자동 배포
  - SSH로 EC2 접속
  - 배포 스크립트 실행
  - 배포 성공/실패 알림

### 3.3 Blue-Green 배포 (선택사항)
- [ ] 무중단 배포 전략 구현
- [ ] Nginx 리버스 프록시 설정
- [ ] 헬스체크 기반 자동 롤백

---

## Phase 4: 모니터링 및 로깅 (선택사항)

### 4.1 애플리케이션 모니터링
- [ ] Spring Boot Actuator 활성화
- [ ] Prometheus + Grafana 설정
- [ ] 주요 메트릭 대시보드 구성

### 4.2 로그 관리
- [ ] CloudWatch Logs 연동 또는
- [ ] ELK Stack (Elasticsearch, Logstash, Kibana) 구축
- [ ] 로그 집계 및 검색 환경 구성

### 4.3 알림 설정
- [ ] Slack/Discord 웹훅 연동
- [ ] 배포 성공/실패 알림
- [ ] 서버 장애 알림

---

## Phase 5: 운영 및 최적화

### 5.1 비용 최적화
- [ ] EC2 인스턴스 타입 최적화
- [ ] RDS 사용 시 Reserved Instance 고려
- [ ] CloudWatch 알람으로 비정상 트래픽 감지

### 5.2 보안 강화
- [ ] SSL/TLS 인증서 설정 (Let's Encrypt)
- [ ] 방화벽 규칙 최소화
- [ ] 정기적인 보안 패치 자동화

### 5.3 백업 및 복구
- [ ] 데이터베이스 자동 백업 설정
- [ ] 스냅샷 정책 수립
- [ ] 재해 복구 계획 (DR Plan) 수립

---

## 참고 자료

### AWS 관련
- [AWS EC2 시작 가이드](https://docs.aws.amazon.com/ec2/)
- [AWS RDS for Oracle](https://docs.aws.amazon.com/rds/)

### GitHub Actions
- [GitHub Actions 문서](https://docs.github.com/en/actions)
- [Docker Build & Push Action](https://github.com/docker/build-push-action)

### Docker
- [Docker Compose 문서](https://docs.docker.com/compose/)
- [Docker 멀티 스테이지 빌드](https://docs.docker.com/build/building/multi-stage/)

---

## 현재 진행 중
- [x] Phase 1.1 기본 CI 워크플로우 설정 (진행 중)
  - [x] PR Validation 워크플로우 완료
  - [ ] Main CI/CD 워크플로우 작성 예정

## 완료된 작업
- ✅ TODO.md 작성 및 프로젝트 목표 정리 (2026-02-15)
- ✅ `.github/workflows/` 디렉토리 생성 (2026-02-15)
- ✅ `pr-validation.yml` 워크플로우 작성 (2026-02-15)
  - Java 17 설정
  - Gradle 캐싱
  - 테스트 실행
  - 빌드 검증
  - 테스트 결과 아티팩트 업로드

## 다음 단계
1. `main-cicd.yml` 작성 (Main 브랜치 CI/CD)
2. Docker 이미지 빌드 및 푸시 설정
3. 실제 PR 생성해서 워크플로우 테스트

---

**마지막 업데이트**: 2026-02-15
