@echo off
mvn clean install -P cli-app
java -jar target/company-management-cli-jar-with-dependencies.jar %1 %2