plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.classgraph:classgraph:4.8.22")
}

application {
    mainClassName = "de.exxcellent.Main"
}