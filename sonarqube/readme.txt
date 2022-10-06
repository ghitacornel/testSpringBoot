STEP 1
Administration -> Configuration -> Security -> Force user authentication -> DISABLE IT !!!

STEP 2
create the project manually and specify local project

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=testSpringBoot \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_8f65afd2155905e2b1c1a23679fed73a0dc543c2

STEP 3
mvn sonar:sonar -Dsonar.projectKey=testSpringBoot -Dsonar.login=sqp_8f65afd2155905e2b1c1a23679fed73a0dc543c2

STEP 4
verify sonarqube report