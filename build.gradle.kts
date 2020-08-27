val avroVersion = "1.10.0"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/gradle/gradle-plugins")
    maven(url = "http://packages.confluent.io/maven/")
}

plugins {
    `maven-publish`
    id("com.commercehub.gradle.plugin.avro") version "0.9.1"
}

dependencies {
    implementation("org.apache.avro:avro:$avroVersion")
}

group = "no.nav.syfo.oppfolgingsplan.avro"
version = "${project.version}"

val githubUser: String by project
val githubPassword: String by project

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/syfoopservice-schema")
            credentials {
                username = githubUser
                password = githubPassword
            }
        }
    }
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            pom {
                name.set("syfoopservice-schema")
                description.set("syfoopservice-schema")
                url.set("https://github.com/navikt/syfoopservice-schema")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/navikt/syfoopservice-schema.git")
                    developerConnection.set("scm:git:https://github.com/navikt/syfoopservice-schema.git")
                    url.set("https://github.com/navikt/syfoopservice-schema")
                }
            }
        }
    }
}
