pipeline {
    agent any

    tools {
        maven '3.8.3'
    }
    triggers {
        pollSCM('')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}