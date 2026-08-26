pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK25'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/amantiwari8861/SpringAiLearning.git'
            }
        }
        stage('Where am I?') {
            steps {
                sh '''
            echo "===== NODE ====="
            hostname
            whoami

            echo "===== JAVA ====="
            echo "JAVA_HOME=$JAVA_HOME"
            which java
            readlink -f $(which java)
            java --version

            echo "===== JDK DIRECTORY ====="
            ls -ld /usr/lib/jvm || true
            ls -ld "$JAVA_HOME" || true

            echo "===== MAVEN ====="
            which mvn || true
        '''
            }
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