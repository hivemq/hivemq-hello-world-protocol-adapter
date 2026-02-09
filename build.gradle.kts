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
    compileOnly("commons-io:commons-io:${property("commons-io.version")}")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")
    compileOnly("org.slf4j:slf4j-api:${property("slf4j.version")}")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:${property("junit.jupiter.version")}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${property("junit.jupiter.version")}")
    testImplementation("org.junit.platform:junit-platform-launcher:${property("junit.jupiter.platform.version")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${property("junit.jupiter.version")}")
    testImplementation("com.hivemq:hivemq-edge-adapter-sdk:${property("hivemq-edge-adapter-sdk.version")}")
    testImplementation("org.mockito:mockito-core:${property("mockito.version")}")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")
    testRuntimeOnly("ch.qos.logback:logback-classic:${property("logback.version")}")
}

tasks.test {
    useJUnitPlatform()
}

license {
    header = file("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}

/* ******************** Visual UI Testing ******************** */

// Configuration for the testing UI module
val testingUi by configurations.creating

dependencies {
    // Add testing UI dependency (uncomment when published to Maven Central)
    // testingUi("com.hivemq:hivemq-edge-adapter-sdk-testing-ui:1.0.0")
}

// Task to launch the visual UI test server
// Run with: ./gradlew testUI
tasks.register<JavaExec>("testUI") {
    group = "verification"
    description = "Launch visual UI test server for adapter configuration testing"

    // Main class from the testing UI module
    mainClass.set("com.hivemq.edge.adapters.testing.AdapterTestServer")

    // Include adapter classes and testing UI on classpath
    classpath = sourceSets.main.get().runtimeClasspath +
            sourceSets.main.get().output +
            configurations.compileClasspath.get()
            // + testingUi  // Uncomment when testing UI is published

    // System properties for configuration
    systemProperty("server.port", project.findProperty("testUI.port") ?: "8080")

    doFirst {
        logger.lifecycle("Starting Adapter Test Server...")
        logger.lifecycle("Adapter JAR: ${sourceSets.main.get().output.classesDirs.asPath}")
    }
}