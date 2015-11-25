<h1> Spring Boot PostgreSQL Local/Cloud Demo</h1>

The following document, goes through how to bind to a data service in IBM Bluemix which is cloud aware. The goal is to 
show how you can develop offline locally and then push the application to IBM Bluemix which will auto bind to a PostgreSQL 
service automatically and is aware it's running in the cloud and setup a data source correctly for this. The goal 
should be a smooth transition from local execution of an application on a developer’s desktop to a test environment in 
IBM Bluemix which is powered by Cloud Foundry

<h2>Deploy to Bluemix</h2>

- clone as follows

```
$ git clone https://github.com/papicella/SpringBootPostgreSQLLocal.git
```

- package using maven

```
$ mvn package
```

- edit manifest.yml file and add your own host/name which is unique, and the PostgreSQL service

```
applications:
 - name: pas-postgresql-local
   memory: 450M
   path: ./target/demo-0.0.1-SNAPSHOT.jar
   instances: 1
   host: pas-postgresql-local
   buildpack: java_buildpack
   domain: mybluemix.net
   services:
    - dev_elephantsql
```

- deploy as follows

```
$ cf push -f cf push -f manifest-BLUEMIX-TIME.yml
```

<h2> Running Locally </h2>

All you have to do is edit application.properties and specify your local PostgreSQL instance so it will connect
to a local PostgreSQL outside Bluemix. 

Note: Comment these out when pushing the JAR to Bluemix

```
# Use your own local PostgreSQL instance
spring.datasource.max-active=10
spring.datasource.initial-size=1
spring.database.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/apples
spring.datasource.username=pas
spring.datasource.password=pas

# For deployment to Bluemix, the connect details are used by spring boot reading the VCAP_SERVICES for PostgreSQL service
#spring.datasource.max-active=5
#spring.datasource.initial-size=1
```

<hr />

Pas Apicella [pasapi at au1.ibm.com] is a Bluemix Technical Specialist at IBM Australia 