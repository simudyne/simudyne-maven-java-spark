# Providence Maven Skeleton

This repository serves as a simple working example of using Providence using the Maven build tool. You will need Maven 
installed locally yourself, or provided through an IDE such as Eclipe or IntelliJ. Because Providence jars are served
from an authenticated artifact repository, you will need to provide information to Maven on where this repository is
located and your credentials.

These settings are located in the `settings.xml` file in this repository. However, these settings are usually located at
`~/.m2/settings.xml`. You can pass the `-s settings.xml` option to point maven at these settings yourself manually,
configure your IDE with a specific settings.xml file, or you may merge the `settings.xml` into your own existing
`settings.xml` if you are already a Maven user. **You need to insert your own credentials as provided by Simudyne into
the `settings.xml` file.**

## Running the project

Included in the pom.xml is configuration for the `exec-maven-plugin`, so you can run the compiled project via
`mvn -s settings.xml exec:java`, which will call the Main.main method.