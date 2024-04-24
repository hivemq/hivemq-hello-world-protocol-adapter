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
    mavenLocal()
    mavenCentral()
}


dependencies {
    compileOnly("com.hivemq:hivemq-edge-extension-sdk:${property("hivemq-edge-extension-sdk.version")}")
    compileOnly("com.hivemq:hivemq-edge-adapter-lib:${property("hivemq-edge-adapter-lib.version")}")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.hivemq:hivemq-edge-extension-sdk:${property("hivemq-edge-extension-sdk.version")}")
    testImplementation("com.hivemq:hivemq-edge-adapter-lib:${property("hivemq-edge-adapter-lib.version")}")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")

}

tasks.test {
    useJUnitPlatform()
}