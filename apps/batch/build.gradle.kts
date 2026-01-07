dependencies {
    // 1. Core 모듈 의존 (Entity, Repository 공유)
    implementation(project(":libs:core"))

    // 2. 스프링 배치 기능
    // (TOML에 spring-boot-starter-batch를 추가했다면 아래처럼 사용)
    // 만약 아직 추가 안 했다면 TOML [libraries]에 먼저 추가해주세요.
    implementation(libs.spring.boot.starter.batch)

    // 예시: 배치는 보통 DB 외에는 별다른 의존성이 많이 필요 없습니다.
    annotationProcessor(libs.spring.boot.configuration.processor)
}
