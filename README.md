# Simudyne Maven Java Skeleton

This repository serves as a simple working example of using Simudyne from Java using the Maven build tool. You will need
Maven installed locally yourself, or provided through an IDE such as Eclipe or IntelliJ. Because Simudyne jars are
served from an authenticated artifact repository, you will need to provide information to Maven on where this repository
is located and your credentials.

These settings are located in the `settings.xml` file in this repository. However, these settings are usually located at
`~/.m2/settings.xml`. You can pass the `-s settings.xml` option to point maven at these settings yourself manually,
configure your IDE with a specific settings.xml file, or you may merge the `settings.xml` into your own existing
`settings.xml` if you are already a Maven user. **You need to insert your own credentials as provided by Simudyne into
the `settings.xml` file.**

## Running the project locally

Included in the pom.xml is configuration for the `exec-maven-plugin`, so you can run the compiled project via
`mvn -s settings.xml exec:java`, which will call the Main.main method.

## Running the project distributed with Spark

- Install Spark

- Start Spark standalone master server : `./sbin/start-master.sh`

- Check the spark Master URL at localhost:8080/ (make sure the console is running on a different port),
you can set the console host and port in your `Main` class with :
```java
Server.setHostName("0.0.0.0");
Server.setPort(8081);
```

- Start one or several slaves : `./sbin/start-slave.sh <sparkMasterURL>`

- Build your fatJar file with `mvn -s settings.xml compile package`, it will be in `target`

- Submit the fatJar using the url from last step: `spark-submit --class Main --master <sparkMasterURL> --deploy-mode client name-of-the-fatjar.jar`