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
                releasedVersion = getReleasedVersion()
                sh 'kubectl set image deployments/localization-api localization-api=maruf571/localization-api:${ releasedVersion }'
            }
        }
    }
}

def getReleasedVersion() {
    return (readFile('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}
