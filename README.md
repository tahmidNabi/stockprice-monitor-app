# stockprice-monitor-app
A simple application to monitor stock prices. Consists of a **REST API** for retrieving, adding and deleting stock symbols, 
as well as getting the trade price history for a particular stock symbol. Also included is a standalone **Cron Job** which
retrieves the latest trade prices for all stock symbols in the database from the **YahooFinance API** and stores them in
the database.

**Prequisities:**
* Java 1.8
* Apache Maven 3.0+
* MySQL

**Pre-build configuration**

1.	Install all the software (Java 1.8, Maven 3.0+, MySQL) mentioned in the prerequisites section
2.	Create a  database user in MySQL with the necessary privileges
3.	Create a database in MYSQL which to store the data
4.	The project is currently configured such that the JPA implementation (Hibernate) will automatically update the schema based on the JPA annotated classes, so manually creating the tables is not necessary.
5.	Add the necessary database credentials in the **application.properties** file. It can be found in $PROJECT_HOME/src/main/resources directory. The necessary credentials needed to be provided are:

      * spring.datasource.url=jdbc:mysql://{dbhostname or IP}:{typically 3306}/{name of the database created in step 3})
      * spring.datasource.username=dbuser (name of the user created in step 2)
      *	spring.datasource.password=dbuserpassword (password of the user created in step2)


**How to build:**

This application is built using Apache Maven. So, just download/clone the repository, open up a terminal/command-line,
traverse to the project directory and run **mvn clean package**. 

e.g.

<code>
~/projects/stockPrice-monitor-app $ mvn clean package
</code>

**How to test:**

The Maven POM is configured to run all unit tests when building the application. The application is configured with TravisCI, so
simply pushing a new commit to Github repo will trigger a build (along with unit tests).

There are integration tests for testing the REST API as well. The Maven POM file is configured to ignore these during normal build.
Integration tests can be run by executing the command **mvn integration-test –P integration** in the terminal/command-line.

**Deployment:**

1.	The project contains both the **REST API** and **stock price fetching service** in a single jar. 
2.	Running the jar will deploy the **REST API**, and the **stock price fetching service** will **run independently**
    to the REST API on an interval specified in the **application.properties** (currently every **30** minutes).
3.	The project can be run directly from the terminal/command-line in the project directory by
    running the command **mvn spring-boot:run**.
    
    e.g.
    
    <code>
      ~/projects/stockPrice-monitor-app $ mvn spring-boot:run
    </code>
    
    
4.	Also, the packaged jar, found in ${PROJECT_HOME}/target directory after building the project
    can also be run from the terminal/command-line using the command **java –jar stock-price-monitor.jar**.
    
    e.g.
    
    <code>
      ~/ $ java –jar stock-price-monitor.jar
    </code>

**REST API calls**

|         | GET(Read)           | POST(Create)  |DELETE(Delete)|
| ------------- |-------------| -----|-----|
| /stocks     | Lists all Stock symbols in the database | | |
| /stocks/GOOGL    |      |  Create a record in the database for a stock having the symbol GOOGL. |Delete the record in the data for a stock having the symbol GOOGL. Also delete corresponding price record history for GOOGL as well.|
| /stockhistoryrecords/GOOGL| Return all price records associated with GOOGL in the database | | |

**Additional details**
Detailed discussion about the project architecture, database design and design patterns used can be found in the PDF report file.


