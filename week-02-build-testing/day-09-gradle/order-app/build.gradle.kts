plugins {
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":order-service"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
}

application {
    mainClass.set("com.tranquilonjoe.day09.OrderApplication")
}