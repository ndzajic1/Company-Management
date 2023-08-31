# Company-Management
Simple JavaFX app. Basic functionalities in a basic company. Employees can log in with their credentials and preview company departments statistics and jobs available in company.
Department managers can preview employees from their department and see basic information.
Admin users can perform CRUD operations on all three entities: employees, departments and jobs.

# GUI Run
```shell
mvn clean install javafx:run
```
or simply:
```shell
./run
```
# CLI Run

```shell
mvn clean install -P cli-app
java -jar target/company-management-cli-jar-with-dependencies.jar [option] [args]
```
