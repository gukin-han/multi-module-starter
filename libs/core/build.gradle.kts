dependencies {
    // 1. DB 및 JPA (여기서 관리)
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.h2) // 로컬 개발용 DB (나중에 MySQL 등으로 교체)

    // 2. Configuration Processor (커스텀 설정 파일 자동완성 지원)
    annotationProcessor(libs.spring.boot.configuration.processor)
}