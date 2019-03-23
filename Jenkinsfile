pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Integration Test') {
            steps {
                sh 'mvn verify'
            }
        }


        stage('Push Image') {
            steps {
                sh 'mvn dockerfile:push'
            }
        }

        stage('Deploy Image') {
            when { branch 'master' }
            steps {
                sh 'kubectl rolling-update localization-api --image=localization-api:1.0.3-SNAPSHOT'
            }
        }
    }
}