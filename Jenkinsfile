pipeline {
    agent any
    tools {
      maven 'Maven 3.8.6'
      jdk 'jdk17'
    }
    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '*/target/surefire-reports/*.xml'
                }
            }
        }
        stage("SonarQube analysis") {
            steps {
              withSonarQubeEnv('SonarQubeServer') {
                sh 'mvn sonar:sonar \
                      -Dsonar.projectKey=testSpringBoot \
                      -Dsonar.host.url=http://localhost:9000'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }
          }
    }
}