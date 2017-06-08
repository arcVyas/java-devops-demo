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
        sh './gradlew clean build -x test'
      }
    }
    stage('Test') {
      steps {
        sh './gradlew test'
        junit 'build/test-results/test/*.xml'
      }
    }
    stage('Quality Check') {
      steps {
        sh './gradlew jacocoTestReport'
        script {
          withSonarQubeEnv('SonarQube') {
            sh './gradlew sonarqube'
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
export PATH=$PATH:/Library/Frameworks/Python.framework/Versions/3.5/bin
echo $PATH
which ansible-playbook
ansible-playbook /Users/vyas/workspace/tools/ansible/tc.yml -i /Users/vyas/workspace/tools/ansible/hosts
'''
      }
    }
  }
}