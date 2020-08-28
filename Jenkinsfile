pipeline {
  agent {
    dockerfile {
      filename 'docker/Dockerfile'
    }

  }
  stages {
    stage('Execute TST tests') {
      steps {
        git 'https://github.com/gchankov/demo-apitests.git'
        dir(path: 'demo-apitests')
        sh 'gradle runTests -Dtarget.environmet=tst'
        sh 'zip /target/html tst-results.zip'
        stash 'tst-results.zip'
      }
    }

  }
}