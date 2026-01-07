# Multi-Module Starter (Spring Boot + Batch)

멀티 모듈 구조로 `api`(웹)와 `batch`(상시 실행형 배치)를 분리해 두고,
공통 코드는 `libs/core`에 모아 확장하기 쉽게 만든 템플릿입니다.
배치 모듈은 스케줄러를 통해 **상시 실행형으로 동작**하도록 설계되어 있습니다.

## 목적

- 멀티 모듈 기반의 **서비스 + 배치** 기본 골격 제공
- 공통 도메인/리포지토리를 `core`로 분리해 **재사용성 확보**
- `api`/`batch` 분리 구조에서 **점진적 확장**이 가능하도록 구성

## 모듈 구조

```
apps/
  api/            # REST API 서버
  batch/          # Spring Batch + Scheduler (상시 실행형)
libs/
  core/           # 공통 도메인/유틸/DB
```

### apps/api
- 웹 요청 처리 전용 모듈
- `server.port=9091`

### apps/batch
- Spring Batch 실행 모듈
- 스케줄러를 통해 상시 실행형 배치 실행
- `server.port=9092`
- 샘플 Job/스케줄러 포함

### libs/core
- 공통 도메인, JPA 설정, 엔티티/리포지토리 위치
- `api`와 `batch`가 함께 의존

## 실행 방법

API 실행:
```
./gradlew :apps:api:bootRun
```

Batch 실행:
```
./gradlew :apps:batch:bootRun
```

## 배치 동작 방식

배치 모듈은 스케줄러 기반 상시 실행 구성을 기본으로 둡니다.

- 스케줄러 활성화: `batch.scheduler.enabled=true`
- 자동 Job 실행: `spring.batch.job.enabled=true`

> 스케줄러만으로 실행하고 싶다면 `spring.batch.job.enabled=false`로 꺼두면 됩니다.

샘플 코드:
- Job 정의: `apps/batch/src/main/java/batch/job/SampleJobConfig.java`
- 스케줄러 트리거: `apps/batch/src/main/java/batch/scheduler/SampleJobScheduler.java`

## 확장 아이디어

- **worker 모듈 분리**: 큐 소비/실시간 처리용 프로세스로 확장
- **batch-core 모듈 추가**: Job 정의를 라이브러리화해 앱 분리 지원
- **프로파일 분리**: 원샷/상시 실행 설정을 `application-*.yml`로 분리
- **DB 분리**: 운영 DB로 교체하고 Batch 메타데이터 스키마 관리

## 설계 원칙

- `core`는 순수 도메인/공통 코드만 유지
- `apps`는 실행 가능한 프로세스 단위로 분리
- 향후 필요 시 **배포 단위**를 손쉽게 분리할 수 있도록 구조화
