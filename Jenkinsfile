pipeline {
  agent any

  environment{
    postgresPass = credentials('postgresPass')
  }

  stages {

    stage('Build') {
      steps {
        sh('docker compose build --build-arg postgresPass="$postgresPass"')
        sh 'docker push jestercharles/ams-backend:1.0.0'
      }
    }

    stage('Deploy') {
      steps {
        sh 'docker compose up -d'
      }
    }

  }
}