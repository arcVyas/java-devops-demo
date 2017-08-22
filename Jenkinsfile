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
    stage('Unit Test') {
      steps {
        sh './gradlew test jacocoTestReport'
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
              slackSend color: "danger", message: "Quality Gate Failed !!(<${env.BUILD_URL}|Open>)"
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
    stage('Deploy to Test Environments') {
      steps {
        echo 'Starting Test Deploys'
      }
    }
    stage('Functional Test') {
      steps {
        echo 'Starting Functional Test'
      }
    }
    stage('Ask Approval') {
      steps {
        slackSend color: "good", message: "Woohoo.. New build ready for deployment. Approve? (<${env.BUILD_URL}|Open>)"
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
        slackSend color: "good", message: "Successfully deployed new version of Demo App - (<http://ec2-13-58-208-59.us-east-2.compute.amazonaws.com:8080/java-devops-demo-app/jdops/tools|Demo App>)"
      }
    }
    stage('Smoke Test') {
      steps {
        echo 'Smoke Test'
      }
    }
  }
}