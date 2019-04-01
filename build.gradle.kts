plugins {
    java
    application
    id ("com.zyxist.chainsaw") version "0.3.1"
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.classgraph:classgraph:4.8.22")
}

application {
    mainClassName = "bentolor.jdkenumerator.JdkDictionaryExtractor"
}

