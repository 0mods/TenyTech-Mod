import net.minecraftforge.gradle.patcher.tasks.ReobfuscateJar
import net.minecraftforge.gradle.userdev.UserDevExtension
import net.minecraftforge.gradle.userdev.tasks.JarJar
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.spongepowered.asm.gradle.plugins.MixinExtension

buildscript {
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7.38")
    }
}

plugins {
    eclipse
    idea
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("net.minecraftforge.gradle") version "6.+"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
}

apply(plugin = "org.spongepowered.mixin")

evaluationDependsOnChildren()

val mod_version: String by project
val mod_group_id: String by project
val minecraft_version: String by project
val forge_version: String by project

val shadow: Configuration by configurations.creating

jarJar.enable()

group = mod_group_id
version = "${minecraft_version}_${mod_version}"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

configurations {
    minecraftLibrary { extendsFrom(shadow) }
}

configure<UserDevExtension> {
    val mappingsChannel: String by project
    val parchmentVersion: String? by project
    if (mappingsChannel == "official") mappings(mappingsChannel, minecraft_version)
    else mappings(mappingsChannel, "${parchmentVersion!!}-${minecraft_version}")

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    copyIdeResources.set(true)

    runs {
        create("client") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "TenyTech")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")
            arg("-mixin.config=tenytech.mixins.json")

            mods {
                create("tenytech") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("server") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "TenyTech")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")
            arg("-mixin.config=tenytech.mixins.json")

            mods {
                create("tenytech") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("data") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            args("--mod", "TenyTech", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))

            mods {
                create("tenytech") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }
    }
}

configure<MixinExtension> {
    add(sourceSets.main.get(), "tenytech.refmap.json")
    config("tenytech.mixins.json")
}

repositories {
    mavenCentral()
    maven("https://maven.blamejared.com")
    maven("https://modmaven.dev")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
    maven("https://maven.tehnut.info")
    maven("https://dvs1.progwml6.com/files/maven")
    maven("https://maven.theillusivec4.top/")
    flatDir {
        dir("libs")
    }
    maven("https://api.modrinth.com/maven")
}

dependencies {
    minecraft("net.minecraftforge:forge:${minecraft_version}-${forge_version}")

    val kffVersion: String by project
    val cucumber_version: String by project
    val mystical_argiculture_version: String by project
    val mystical_argradditions_version: String by project
    val mantle_version: String by project
    val tc_version: String by project
    val curiosVersion: String by project
    val jeiVersion: String by project

    jarJar(fg.deobf("thedarkcolour:kotlinforforge:${kffVersion}")) {
        jarJar.ranged(this, "[$kffVersion,)")
    }

    runtimeOnly(fg.deobf("thedarkcolour:kotlinforforge:${kffVersion}"))
    implementation(fg.deobf("com.blakebr0.cucumber:Cucumber:${minecraft_version}-${cucumber_version}"))
    implementation(fg.deobf("com.blakebr0.cucumber:MysticalAgriculture:${minecraft_version}-${mystical_argiculture_version}"))
    implementation(fg.deobf("com.blakebr0.cucumber:MysticalAgradditions:${minecraft_version}-${mystical_argradditions_version}"))

    implementation(fg.deobf("slimeknights.tconstruct:Mantle:${minecraft_version}-${mantle_version}"))
    implementation(fg.deobf("slimeknights.tconstruct:TConstruct:${minecraft_version}-${tc_version}"))

    implementation(fg.deobf("top.theillusivec4.curios:curios-forge:${minecraft_version}-${curiosVersion}"))

    implementation(fg.deobf("journeymap:journeymap:1.16.5-5.8.5p6"))

    compileOnly(fg.deobf("mezz.jei:jei-${minecraft_version}:${jeiVersion}:api"))
    runtimeOnly(fg.deobf("mezz.jei:jei-${minecraft_version}:${jeiVersion}"))

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<Jar> {
        from(sourceSets.main.get().output)
        manifest {
            attributes(
                mapOf(
                    "Specification-Title" to "TenyTech",
                    "Specification-Vendor" to "AlgorithmLX",
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to version,
                    "Implementation-Timestamp" to ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")),
                    "MixinConfigs" to "tenytech.mixins.json"
                )
            )
        }
        finalizedBy("reobfJar")
    }

    withType<ReobfuscateJar> {
        jarJar
    }

    withType<JarJar> {
        from(provider { shadow.map(::zipTree).toTypedArray() })
        finalizedBy("reobfJarJar")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}