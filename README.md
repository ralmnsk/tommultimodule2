# tommultimodule2
There is an application with such technologies as Spring Boot, Spring MVC, Spring Security, Spring Data, Hibernate, mySQL, JSP.

The News portal project is a multi module web application. To simplify the process of the project development Maven was used. There are dao layer, model module, web and service modules in the application. News portal is connected to the mySQL database where users, news data, messages and other information are stored. 

Users, administrator and unregistered portal guests have different access levels and menu.
The unregistered user can read the news.
The registered user can also read news, but creates, edits, deletes and comments.
The administrator has the same possibilities as users and even more. He can change news and messages of users.
Spring security helps to maintain roles and privileges of users and also such processes as registration, authentication and authorization.

The Dao layer is created by using Spring Data JPA technology and Hibernate to connect to the database.
Models are POJO classes fulfilled with Hibernate annotations.
The Service layer includes application logic and communicates with all project modules.
To view and interact with the application I included Spring Web MVC layer and JSP to create dynamically generated web pages.
The pagination of news, contacts and messages was created to watch separated pages and simple navigation. 
 

