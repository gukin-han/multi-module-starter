dependencies {
    // 1. 핵심: Core 모듈 의존 (도메인, DB 기능 가져오기)
    implementation(project(":libs:core"))

    // 2. 웹 서버 기능 (MVC, Tomcat)
    implementation(libs.spring.boot.starter.web)

    // 3. 인증 (JWT Bundle 사용 - TOML에서 묶어둔 3개가 한 번에 들어옴)
    implementation(libs.bundles.jjwt)

    // 4. 설정 편의성
    annotationProcessor(libs.spring.boot.configuration.processor)
}