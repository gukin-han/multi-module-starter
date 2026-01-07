import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    alias(libs.plugins.springBoot) apply false
    alias(libs.plugins.dependencyManagement) apply false
}

// 2. 프로젝트 공통 정보
group = "com.example"
version = "0.0.1-SNAPSHOT"

// 3. 모든 서브 모듈(:apps:..., :libs:...)에 공통 적용되는 설정
subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    // 4. 자바 버전 설정 (TOML에서 가져옴)
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(rootProject.libs.versions.java.get()))
        }
    }

    // 5. 공통 의존성 (Lombok, Test)
    dependencies {
        // 롬복은 모든 모듈에서 씀
        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        // 테스트 라이브러리 (JUnit5, Mockito 등이 포함된 스타터 사용)
        // 아까 작성하신 'platform' 방식보다 이게 훨씬 깔끔하고 버전 관리도 자동입니다.
        testImplementation(rootProject.libs.spring.boot.starter.test)
    }

    // 6. 테스트 설정 (JUnit 5 활성화)
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }

    // 7. [핵심] Apps는 실행 가능(BootJar), Libs는 라이브러리(Jar)로 설정
    // apps로 시작하는 프로젝트인지 확인
    if (project.path.startsWith(":apps")) {
        tasks.named<BootJar>("bootJar") {
            enabled = true  // 실행 가능한 Jar 생성
        }
        tasks.named<Jar>("jar") {
            enabled = false // 일반 Jar 생성 안 함 (선택사항)
        }
    } else {
        tasks.named<BootJar>("bootJar") {
            enabled = false // 실행 불가능 (Main 클래스 없음)
        }
        tasks.named<Jar>("jar") {
            enabled = true  // 다른 모듈에서 쓸 수 있게 일반 Jar 생성
        }
    }
}