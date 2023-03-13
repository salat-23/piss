plugins {
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.yvasyliev:java-vk-bots-longpoll-api:4.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "me.salat23.piss.MainKt"
    }
}
