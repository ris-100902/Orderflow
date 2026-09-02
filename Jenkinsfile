pipeline {
    agent any
    stages{
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps{
                echo 'Build stage'
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
}