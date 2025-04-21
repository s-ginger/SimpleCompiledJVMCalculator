plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.antlr:antlr4-runtime:4.13.1")
    implementation("org.ow2.asm:asm:9.6")
    implementation("org.ow2.asm:asm-util:9.6")
}

tasks.test {
    useJUnitPlatform()
}