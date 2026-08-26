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
            echo "===== JAVA ====="
            echo "JAVA_HOME=$JAVA_HOME"
            which java
            readlink -f $(which java)
            java --version

            echo "===== JAVA_HOME ====="
            ls -ld "$JAVA_HOME"
            ls -l "$JAVA_HOME/bin/java"
            "$JAVA_HOME/bin/java" --version

            echo "===== MAVEN ====="
            which mvn
            ls -l $(which mvn)
            mvn --version
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