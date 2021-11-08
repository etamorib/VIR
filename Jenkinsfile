pipeline {
    agent any

    tools {
        maven "maven"
        jdk "jdk11"
    }
    triggers {
        pollSCM('')
    }

    stages {
        stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}