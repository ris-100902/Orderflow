def helpers
pipeline {
    agent any
    tools {
        jdk 'JDK25'
    }
    stages{
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps{
                script {
                    echo 'Build stage'
                    sh 'java -version'
                    echo "JAVA_HOME = ${env.JAVA_HOME}"
                    helpers = load 'jenkins/buildJavaService.groovy'
                    helpers.buildService()
                }
            }
        }

        stage('Test') {
            steps{
                echo 'Testing'
                sh './gradlew test'
            }
        }

        stage('Package') {
            steps{
                sh './gradlew bootJar'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    helpers = load 'jenkins/buildAndPushImage.groovy'
                    env.IMAGE_TAG = helpers.buildImage('orderflow')
                }
            }
        }
    }

    post {
        always {
            echo 'Finished job'
            archiveArtifacts artifacts: '''
                build/libs/*.jar,
                build/logs/*.log
            ''', fingerprint: true
            junit 'build/test-results/test/*.xml'
            cleanWs()
        }
        success {
            echo 'Success'
        }
        unstable{
            echo 'Unstable'
        }
        failure {
            echo 'Failure'
        }
    }
}