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
                sh 'mvn clean verify sonar:sonar \
                      -Dsonar.projectKey=testSpringBoot \
                      -Dsonar.host.url=http://localhost:9000 \
                      -Dsonar.login=sqp_1b08e0d3787d65c34a0e278320c61552e6343528'
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