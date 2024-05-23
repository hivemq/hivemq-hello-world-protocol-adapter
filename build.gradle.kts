plugins {
    id("java")
    id("com.github.sgtsilvio.gradle.utf8")
    id("com.github.johnrengelman.shadow")
    id("com.github.hierynomus.license")
    id("org.owasp.dependencycheck")
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    compileOnly("com.hivemq:hivemq-edge-adapter-sdk:${property("hivemq-edge-adapter-sdk.version")}")

    compileOnly("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")
    compileOnly("org.slf4j:slf4j-api:${property("slf4j.version")}")
}

dependencies {
    testImplementation("com.hivemq:hivemq-edge-adapter-sdk:${property("hivemq-edge-adapter-sdk.version")}")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")
}

tasks.test {
    useJUnitPlatform()
}