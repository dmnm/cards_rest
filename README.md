# Cards Rest API Example  
  
## How to run
Please run from parent directory:  
`mvn package spring-boot:run -pl cards-rest`  
or  
`mvn clean package && java -jar cards-rest/target/cards-rest-0.0.1-SNAPSHOT.jar`  

## How to use
First of all please visit page bellow to authorize a user:  
`localhost:8080/auth` - Login: user, password: 123  

The you can invoke some requests:  
  
`localhost:8080/api/v1/loans` - all user loans (mock data), GET method only  
`localhost:8080/api/v1/deposits` - all user deposits (mock data), GET method only  
`localhost:8080/api/v1/cards` - all user cards (mock data), GET method only  
`localhost:8080/api/v1/cards/{cardId}/block` - to block selected card, PUT method only  
`localhost:8080/api/v1/cards/{cardId}/unblock` - to unblock selected card, PUT method only  

## Modules:  
`common` - contains common interfaces and dtos  
`login` - module for authorization module, contains mock implementstion of LoginFacade  
`abc` - (have no idea how it should be named)) module for bank operation, contains mock implementstion of Loan, Deposit and Card Facades  
`cards-rest` - main module: rest, security and so on


#### Links
Custom 'Remember me' authentication with token -  
http://automateddeveloper.blogspot.ru/2014/03/securing-your-mobile-api-spring-security.html
