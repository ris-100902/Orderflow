def buildService() {
    sh './gradlew clean compileJava --rerun-tasks'
}
return this