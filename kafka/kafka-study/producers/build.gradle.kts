plugins {
    java
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.5.1")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    implementation("com.github.javafaker:javafaker:1.0.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
