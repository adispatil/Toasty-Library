pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/adispatil/Toasty-Library")
            credentials {
                username = providers.gradleProperty("githubUsername").getOrElse("adispatil")
                password = providers.gradleProperty("githubToken").getOrElse("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")
            }
        }
    }
}

rootProject.name = "Toast Library"
include(":app")
include(":toastylibrary")
