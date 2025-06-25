pipeline{
    agent {
        any
    }
    stages{
        stage('Check out Code'){//stage to checkout code from SCM
            echo "Checking out code from SCM"
            checkout scm
        }// end stage

        stage('Run Tests'){//stage to execute tests
            echo "Running tests for given cucumber tag"
            sh mvn clean install test -Dcucumber.filter.tag={$Tag}
        }//end stage

        stage('Archive Reports'){//stage to archive reports
            echo "Archiving reports"
            archiveArtifacts artifact = 'target/Reports/Latest/**/*.*'
        }//end stage
    }

    post{
        always{
            echo "Pipeline Completed"
        }
    }
}