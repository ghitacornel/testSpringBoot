application.version=${project.version}
application.formatted-version=formatted version ${project.version}
application.title=My Demo Application Title

#context path for custom application context path
#server.servlet.context-path=/testSpringBoot

#logging
#debug=true

#json pretty print
spring.jackson.serialization.indent_output=true

#freemarker
spring.freemarker.contentType=text/html;charset=UTF-8

#actuator
management.endpoints.web.exposure.include=*

#compress server responses
server.compression.enabled=true

#DATASOURCE
spring.datasource.url=jdbc:postgresql://postgres:5432/postgresql_database
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=cornel
spring.datasource.password=sefusefu

#JPA
database.dialect=org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.hibernate.format_sql=true
spring.jpa.show-sql=true

#lazy Hibernate ByteBuddy init and Jackson JSON
#spring.jackson.serialization.fail-on-empty-beans=false

spring.flyway.enabled=true