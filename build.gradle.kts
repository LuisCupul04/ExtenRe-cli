import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
    application
    `maven-publish`
    signing
}

group = "com.extenre"

val githubUser = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
val githubToken = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")

application {
    mainClass = "com.extenre.cli.command.MainCommandKt"
}

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://maven.pkg.github.com/LuisCupul04/ExtenRe-library")
        credentials {
            username = githubUser ?: ""
            password = githubToken ?: ""
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/LuisCupul04/ExtenRe-patcher")
        credentials {
            username = githubUser ?: ""
            password = githubToken ?: ""
        }
    }
}

dependencies {
    implementation(libs.extenre.patcher)
    implementation(libs.extenre.library)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.picocli)
    implementation(libs.gson)

    testImplementation(libs.kotlin.test)
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }

    processResources {
        expand("projectVersion" to project.version)
    }

    shadowJar {
        archiveClassifier.set("all")
        exclude("/prebuilt/linux/aapt", "/prebuilt/windows/aapt.exe", "/prebuilt/*/aapt_*")
        minimize {
            exclude(dependency("org.bouncycastle:.*"))
            exclude(dependency("com.extenre:extenre-patcher"))
        }
    }
}

// Configuración de publicación Maven
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/LuisCupul04/ExtenRe-cli")
            credentials {
                username = githubUser ?: ""
                password = githubToken ?: ""
            }
        }
    }
    publications {
        create<MavenPublication>("extenre-cli-publication") {
            artifact(tasks.shadowJar)
            from(components["java"])
        }
    }
}

// Firma (coméntalo si no usas GPG)
signing {
    useGpgCmd()
    sign(publishing.publications["extenre-cli-publication"])
}