# innso-customer-tracking

## Requirements
* Git
* Java 8
* Maven 3.6

## How to launch

Download the code source from gitlab:
```
git clone https://gitlab.com/quanghuy.tran33/innso-customer-tracking.git
```

Generate package Java with this code:
``` 
mvn clean install
```

Then launch the application
``` 
java -jar target/innso-customer-tracking-0.0.1-SNAPSHOT.jar
```

## How to test

After launching the application, the QA can test the api via Swagger UI 
by connecting to this URL

``
http://localhost:9999/api/swagger-ui.html
``