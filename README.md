# Spring Boot Backend Practice

## 1. 프로젝트 소개

Java와 Spring Boot를 활용하여 게시글 및 회원 관리 REST API를 구현한 프로젝트입니다.

## 2. 개발 환경

- Java 21
- Spring Boot 4.1.1
- Maven
- IntelliJ IDEA
- Postman

## 3. 구현 기능

### 게시글 API

| Method | Endpoint | 기능 |
|---|---|---|
| POST | `/api/posts` | 게시글 등록 |
| GET | `/api/posts/{id}` | 게시글 조회 |
| PUT | `/api/posts/{id}` | 게시글 수정 |
| DELETE | `/api/posts/{id}` | 게시글 삭제 |

### 회원 API

| Method | Endpoint | 기능 |
|---|---|---|
| POST | `/api/members` | 회원 등록 |
| GET | `/api/members` | 전체 회원 조회 |
| PUT | `/api/members/{id}` | 회원 수정 |
| DELETE | `/api/members/{id}` | 회원 삭제 |

## 4. 응답 형식

API 응답은 JSON 형식으로 반환하며, 공통 응답 객체를 활용하여 응답 구조를 통일하도록 구현했습니다.

성공 응답 예시:

```json
{
  "status": "success",
  "data": {}
}
```

## 5. 실행 방법

프로젝트 폴더에서 다음 명령어를 실행합니다.

Windows:

```bat
mvnw.cmd spring-boot:run
```

Maven Wrapper가 없는 경우 Maven이 설치되어 있어야 합니다.

서버 기본 주소:

`http://localhost:8080`

## 6. 테스트

Postman을 사용하여 게시글 및 회원 API의 요청과 응답을 테스트했습니다.

실행 및 테스트 결과는 제출용 스크린샷에서 확인할 수 있습니다.

## 7. 구현 및 보완 사항

게시글 및 회원 CRUD API를 구현하고 Postman을 통해 동작을 확인했습니다.

요청 로깅, 예외 처리, HTTP 상태 코드별 응답 및 테스트 등 세부 항목의 최종 충족 여부는 실제 구현 상태와 테스트 결과를 기준으로 확인해야 합니다.
