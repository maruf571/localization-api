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
                sh 'kubectl set image deployments/localization-api localization-api=docker.io/maruf571/localization-api:${ version() }'
            }
        }
    }
}

def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+?)</version>'
    return matcher ? matcher[0][1] : null
}

