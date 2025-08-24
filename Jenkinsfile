pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'chmod +x gradlew'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew clean test'
            }
        }
    }

    post {
        always {
            allure includeProperties: false, 
                   results: [[path: 'build/allure-results']]
        }
    }
}
