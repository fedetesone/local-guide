plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.localguide"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
}

val springAiVersion: String by extra { "1.0.0-M2" }

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("dev.langchain4j:langchain4j-open-ai:0.33.0")
	implementation("dev.langchain4j:langchain4j:0.33.0")
	implementation("dev.langchain4j:langchain4j-easy-rag:0.33.0")

	// Object mapper
	implementation("org.mapstruct:mapstruct:1.6.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")

	// In-memory DB
	runtimeOnly("com.h2database:h2")

	// Redis
	// implementation("org.springframework.ai:spring-ai-redis-store-spring-boot-starter")

	///////////////////////// Test dependencies /////////////////////////
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Test containers
	testImplementation("org.testcontainers:junit-jupiter:1.20.1")
	testImplementation("com.redis:testcontainers-redis:2.2.2")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.ai:spring-ai-bom:$springAiVersion")
	}
}

tasks.test {
	useJUnitPlatform()
}
