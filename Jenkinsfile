pipeline {
  agent any
  stages {
    stage('Docker Build') {
      steps {
        sh 'docker build -t jestercharles/ams-backend:1.0.0 .'
      }
    }

    stage('Docker Login & Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHubId', passwordVariable: 'dockerHubIdPassword', usernameVariable: 'dockerHubIdUser')]) {
          sh "docker login -u ${env.dockerHubIdUser} -p ${env.dockerHubIdPassword}"
          sh 'docker push jestercharles/ams-backend:1.0.0'
        }
      }

    stage('Docker Push') {
      steps {
        sh 'docker run -p 8080:9999 jestercharles/ams-backend:1.0.0'
      }
    }

    }
  }
}