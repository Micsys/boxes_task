#Boxes_Task – Fundraiser Box Manager

#Built with: 
-Java 17
-Spring Boot
-Spring Web
-Spring Data JPA
-H2 in-memory database
-Maven

#How to Run
- Open `BoxesTaskApplication.java`
- Run the file

#API Endpoints
Endpoint:                  	Method:	URL:                          
Create fundraising event   	POST   	`/events`                    
List events                	GET    	`/events`                    
Register new box           	POST   	`/boxes`                     
List all boxes             	GET    	`/boxes`                     
Unregister box             	DELETE 	`/boxes/{id}`                
Assign box to event        	POST   	`/boxes/{id}/assign/{event}` 
Add money to box           	POST   	`/boxes/{id}/money`          
Empty box to event account 	POST   	`/boxes/{id}/empty`          

#H2 Database
-URL: http://localhost:8080/h2-console  
-JDBC: jdbc:h2:mem:boxesdb  
-User: sa  
-Password: *empty*

#Testing
-Postman or test.http in resurces