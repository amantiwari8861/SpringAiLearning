pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/amantiwari8861/SpringAiLearning.git'
            }
        }
        stage('Verify Environment') {
            steps {
                sh '''
                    echo "JAVA_HOME=$JAVA_HOME"
                    java --version
                    mvn --version
                '''
            }

        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run') {
            steps {
                sh 'java -jar target/SpringAiLearning-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}