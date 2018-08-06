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

**Make sure to setup your configuration in the `simudyneSDK.properties` file.** 

## Running the project locally

Included in the pom.xml is configuration for the `exec-maven-plugin`, so you can run the project via
`mvn -s settings.xml compile exec:java`, which will compile the project and then call the Main.main method.

## Running the project distributed with Spark

- Install Spark

### Running on Spark standalone

- Start Spark standalone master server : `./sbin/start-master.sh`

- Check the spark Master URL at localhost:8080/ (make sure the console is running on a different port),
you can set the console host and port in your `simudyneSDK.properties` file in the `NEXUS-SERVER` section.

- Start one or several slaves : `./sbin/start-slave.sh <sparkMasterURL>`

### Running on Spark on Yarn

- Your `<sparkMasterURL>` will be `yarn`.
  
- We have tested with: 
  * Cloudera CDH 5.13.1, 2.3.0.cloudera2
  * Cloudera CDH 5.15.0, 2.2.0.cloudera2
  * Cloudera CDH 5.15.0, 2.3.0.cloudera3

### Next steps

- Use the SimudyneSDK.properties file to set the master URL and other settings

- Add `core-abm.backend-implementation=simudyne.core.graph.spark.SparkGraphBackend` to SimuydneSDK.properties file

- Build your fatJar file with `mvn -s settings.xml clean package`, it will be in `target`
```shell
mvn -s settings.xml clean package \
&& hdfs dfs -put -f target/simudyne-maven-java-spark-1.0-SNAPSHOT.jar /user/${USER}/
```

- SSH into your master node and then submit the fatJar using the url from last step: 
```shell
spark2-submit --master yarn --deploy-mode client \
--conf "spark.executor.extraJavaOptions=-XX:+UseG1GC" --class Main \
--files simudyneSDK.properties hdfs:///user/${USER}/simudyne-maven-java-spark-1.0-SNAPSHOT.jar
```

- You should set `--num-executors`,  `--executor-cores`, `--executor-memory` parameters according your own cluster resources.
Useful resource : [http://blog.cloudera.com/blog/2015/03/how-to-tune-your-apache-spark-jobs-part-2/](http://blog.cloudera.com/blog/2015/03/how-to-tune-your-apache-spark-jobs-part-2/)

## Running multiple runs distributed with spark

- Add `core-runner.runner-backend = simudyne.core.runner.spark.SparkRunnerBackend` to SimuydneSDK.properties file

- Run project on Spark as above

- Use console multirun setting to run model multiple times
