pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/amantiwari8861/SpringAiLearning'
            }
        }
    }

        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }
    stage('run') {
        steps {
            sh 'mvn spring-boot:run'
        }
    }
}