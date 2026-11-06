plugins {
    base
}

group = "com.tranquilonjoe.day09"
version = "1.0-SNAPSHOT"

tasks.register("printBuildInfo") {
    group = "verification"
    description = "Prints basic information about the application build"

    val buildProjectName = project.name
    val buildGroup = project.group.toString()
    val buildVersion = project.version.toString()

    doLast {
        println("Project: $buildProjectName")
        println("Group: $buildGroup")
        println("Version: $buildVersion")
        println("Java: ${System.getProperty("java.version")}")
    }
}

tasks.register("prepareDeployment") {
    group = "deployment"
    description = "Prepares the application for deployment"

    mustRunAfter("printBuildInfo")
    finalizedBy("cleanupDeployment")

    doLast {
        println("Preparing deployment...")
        println("Deployment preparation completed.")
    }
}

tasks.register("cleanupDeployment") {
    group = "deployment"
    description = "Cleans up temporary deployment resources"

    doLast {
        println("Cleaning up deployment resources...")
        println("Cleanup completed.")
    }
}