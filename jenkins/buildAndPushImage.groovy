def buildImage(String imageName) {
    def commitSHA = sh(
        script: 'git rev-parse HEAD',
        returnStdout: true
    ).trim()

    def imageTag = "${imageName}:${commitSHA}"
    def archiveName = "${imageName}-${commitSHA}.tar"

    echo "Building Docker Image: ${imageTag}"

    sh "docker build -t ${imageTag} ."
    sh "docker save ${imageTag} -o ${archiveName}"

    archiveArtifacts artifacts: archiveName, fingerprint: true

    return imageTag
}

return this