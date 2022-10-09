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
                      -Dsonar.host.url=http://sonarQube:9000 \
                      -Dsonar.login=sqp_b6f53a9f719ac5311379132a8db45577d3a5fb92'
              }
            }
          }
//           stage("Quality Gate") {
//             steps {
//               timeout(time: 5, unit: 'MINUTES') {
//                 waitForQualityGate abortPipeline: true
//               }
//             }
//           }
    }
}