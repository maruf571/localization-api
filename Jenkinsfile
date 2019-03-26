pipeline {

    agent any
    environment {
     image = "maruf571/localization-api:1.0.6"
    }

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
                sh 'kubectl apply -f localization-api-deployment.yml'
               // sh 'kubectl set image deployments/localization-api localization-api=docker.io/${image}'
            }
        }
    }
}
