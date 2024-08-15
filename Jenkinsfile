pipeline {

  environment {
    dockerHubId = 'dockerHubId'
  }
  agent any

  stages {

    stage('Build') {
      steps {
        sh 'docker build -t jestercharles/ams-backend:1.0.0 .'
        sh 'docker push jestercharles/ams-backend:1.0.0'
        }
      }
    }

    stage('Deploy') {
      steps {
        sh 'docker run -p 8080:9999 jestercharles/ams-backend:1.0.0 -e postgresPass=${postgresPass}'
      }
    }

  }
}