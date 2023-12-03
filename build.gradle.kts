import org.jetbrains.kotlin.backend.common.compilationException
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    val kotlinVersion = "1.8.22"
    val palantirDockerVersion = "0.35.0"

    kotlin("jvm") version "$kotlinVersion"
    kotlin("plugin.spring") version "$kotlinVersion"
    kotlin("plugin.jpa") version "$kotlinVersion"

    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    id("com.palantir.docker") version "$palantirDockerVersion"
}

group = "br.com.rfasioli"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.jar {
    enabled = false
}
tasks.bootJar {
    enabled = true
}

repositories {
    mavenCentral()
}

val springCloudVersion = "2020.0.3"

configurations {
    implementation {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-rabbit")

    implementation("org.springdoc:springdoc-openapi-ui:1.5.12")

//    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:1.32.0")
//    implementation('io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter:1.32.0-alpha')

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

docker {
    name = "${project.name.toLowerCase()}"
    files("$projectDir/build/libs/${project.name.toLowerCase()}-${project.version}.jar")
    setDockerfile(file("$projectDir/Dockerfile"))
}
