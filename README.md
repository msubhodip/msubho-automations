# Test-Automation

This repo contains the automation tests

### First Time Configuration Steps
- Take the repo
- Install and configure Maven (help https:maven.apache.org/install.html) and configure MAVEN_HOME as environment variable
- Install java jdk and configure JAVA_HOME as environment variable

### How to execute test Locally
 Use the command in terminal/command line : 
 
 mvn clean install -Dbrowser="chrome" -Denvironment="dev" -Dcucumber.options="--tags @test2"