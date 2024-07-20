plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
}

group = "kr.eme.semiTerminal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/") //ProtocolLib
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.2.0-SNAPSHOT")
//    compileOnly("org.spigotmc:spigot:1.20.2-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
    destinationDirectory = file("d:\\minecraft\\1. 버킷 관련\\1.20.2 paper_dev2\\plugins")
    manifest {
        attributes["main-class"] = "kr.eme.semiTerminal.SemiTerminal"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "kr.eme.semiTerminal"
            artifactId = project.name
            version = project.version.toString()
            from(components["kotlin"])
        }
    }
}