plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    val logbackVersion = "1.2.11"
    val lombokVersion = "1.18.24"
    val slf4jVersion = "1.7.36"
    val mockitoVersion = "4.6.1"
    val jacksonVersion = "2.13.3"
    val junitVersion = "5.8.2"


    // lombok
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")

    // logging
    implementation("org.slf4j:jcl-over-slf4j:${slf4jVersion}")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")

    // testing
    testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${junitVersion}")

    testImplementation("org.mockito:mockito-core:${mockitoVersion}")
    testImplementation("org.mockito:mockito-junit-jupiter:${mockitoVersion}")

    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")

    testImplementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
}

tasks.test {
    useJUnitPlatform()
}
