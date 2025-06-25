pipeline {
    agent any

    parameters {
        string(name: 'Tag', defaultValue: '@Smoke', description: 'Enter Cucumber Tag')
    }

    stages {
        stage('Print Tag') {
            steps {
                echo "Running for Tag: ${params.Tag}"
            }
        }
    }

    post {
        always {
            echo "Pipeline Completed"
        }
    }
}
