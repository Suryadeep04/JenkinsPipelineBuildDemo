pipeline {
    agent {
        any
    }

    parameters {
        choice(name: 'Tag', choices: ['@TC1','@TC2'], description: 'Select the cucumber Tag to run')
    }

    stages {
        stage('Check out Code') {//stage to checkout code from SCM
            steps {
                echo "Checking out code from SCM"
                checkout scm
            }
        }// end stage

        stage('Run Tests') {//stage to execute tests
            steps {
                echo "Running tests for given cucumber tag"
                sh "mvn clean install test -Dcucumber.filter.tags=${params.Tag}"
            }
        }//end stage

        stage('Archive Reports') {//stage to archive reports
            steps {
                echo "Archiving reports"
                archiveArtifacts artifacts: 'target/Reports/Latest/**/*.*'
            }
        }//end stage
    }

    post {
        always {
            echo "Pipeline Completed"
        }
    }
}