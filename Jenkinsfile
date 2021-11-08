pipeline {
    agent any

    tools {
        maven 'maven3.8'
    }
    triggers {
        githubPush()
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}