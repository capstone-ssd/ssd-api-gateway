# ssd-api-gateway

SSD MSA 구조의 외부 진입점 역할을 담당하는 Spring Cloud Gateway 서비스다.

## 역할

- 외부 HTTP 요청의 단일 진입점
- 내부 서비스 라우팅
- 공통 헤더 전달
- 향후 인증 필터와 CORS 정책의 집중 지점

## 현재 범위

- `auth-service`
- `member-service`
- `document-service`
- `review-service`
- `ai-orchestrator`

## 실행

```bash
./gradlew bootRun
```
