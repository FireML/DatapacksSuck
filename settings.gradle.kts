rootProject.name = "DatapacksSuck"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Paper API
            library("paper-api", "io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")

            // compileOnly dependencies
            library("daisylib", "uk.firedev:DaisyLib:2.9.0-SNAPSHOT")

            // implementation dependencies

            // paperLibrary dependencies

            // Gradle plugins
            plugin("shadow", "com.gradleup.shadow").version("9.0.0")
            plugin("plugin-yml", "de.eldoria.plugin-yml.paper").version("0.7.1")
        }
    }
}
