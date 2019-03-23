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


        stage('Deploy') {
            steps {
                sh 'mvn dockerfile:push'
            }
        }

        stage('Deploy') {
            when { branch 'master' }
            steps {
                sh 'mvn dockerfile:push'
                sh 'kubectl apply -f deployment.yaml'
            }
        }
    }
}