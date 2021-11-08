pipeline {
    agent any

    tools {
        maven 'maven3.8'
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