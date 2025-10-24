plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.niki914"
version = "1.0-SNAPSHOT"

val kotlinVersion = "2.1.20"

dependencies {
    // Kotlin 标准库
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    // Reflections 库
    implementation("org.reflections:reflections:0.10.2")

    // SLF4J NOP
    implementation("org.slf4j:slf4j-nop:1.7.32")

    // ASM
    implementation("org.ow2.asm:asm:9.8")

    // ASM Tree
    implementation("org.ow2.asm:asm-tree:9.8")

    // Kotlin 测试
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}