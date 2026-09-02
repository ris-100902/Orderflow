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
                echo 'Build stage'
                sh 'java -version'
                echo "JAVA_HOME = ${env.JAVA_HOME}"
                sh './gradlew clean compileJava'
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
    }

    post {
        always {
            echo 'Finished job'
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