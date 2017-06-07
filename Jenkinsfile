pipeline {
  agent any
  stages {
    stage('Initiallize') {
      steps {
        echo 'Starting build'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew clean build '
      }
    }
    stage('Publish Test Reports') {
      steps {
        junit 'build/test-results/test/*.xml'
      }
    }
    stage('Quality Check') {
      steps {
        script {
          withSonarQubeEnv('SonarQube') {
            sh './gradlew --info sonarqube'
          }
        }
        
      }
    }
    stage('Quality Gate') {
      steps {
        script {
          timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }else{
              echo "Quality Gate status : ${qg.status}"
            }
          }
        }
        
      }
    }
    stage('Publish Artifacts') {
      steps {
        sh './gradlew publish '
      }
    }
    stage('Ask Approval') {
      steps {
        slackSend 'Woohoo.. Ready for deployment '
        input(message: 'Can I deploy?', ok: 'Go Ahead', id: '_ready')
      }
    }
    stage('Deploy') {
      steps {
        sh '''export ANSIBLE_HOME=/Users/vyas/workspace/tools/ansible
cd $ANSIBLE_HOME
pwd
cd -
pwd'''
      }
    }
  }
}