# BirthdayTracker

* A CRUD (Create, Read, Update, Delete) dynamic webpage program that connects to MySQL database with JDBC. 

* HTTP requests/responses handled by Servlets. 

* DAO layer implements CRUD features rather than servlets for easier code readability and maintenance. 

* MVC pattern (Model, View, Controller)

![Main Page After Login](/project_images/mainPageSorted.png)

## Some of the Features: 
	 Login page. 
 
	 Can register to save birthday table data.
  
	 Can proceed as a Guest which will NOT save the birthday table data. 
  
	 MySQL database users registered in credentials table. Passwords hashed and salted with BCrypt.
 
	 ★Auto updating countdown days till next birthday.
 
	 ★Auto updating next birthday date.

	 ★Auto updating Age upon having a birthday pass
 
	 ★You only have to specify age, month, date. Because who knows their friends birth year?
 
	 ★Sort table by various options such as countdown days, age, name ascending or descending - your choice.
	
Implementation Steps:

	1) Set web.xml jdbcURL, jdbcUsername, jdbcPassword 
	```
			<web-app>
		  <display-name>Archetype Created Web Application</display-name>
		 <context-param>
			<param-name>jdbcURL</param-name>
			<param-value>jdbc:mysql://localhost:3306/birthdays</param-value>
		 </context-param>
		 
		 <context-param> 
			<param-name>jdbcUsername</param-name>
			<param-value>root</param-value>
		 </context-param>
		  
		  <context-param>
			<param-name>jdbcPassword</param-name>
			<param-value>notSaying</param-value>
		  </context-param> 
		```  
		Choose correct driver for your database. I used MySQL so i have MySQL driver. 
  
		Tomcat server. You can choose any server.
	
	2) Create MySQL database and 2 tables:
 
		0) CREATE DATABASE birthdays; (don't forget web.xml jdbcURL)
  ```
		1) CREATE TABLE creds(
  
			id smallint UNSIGNED AUTO_INCREMENT,
			name VARCHAR(45),
			password VARCHAR(60),
			PRIMARY KEY (id)
			);
		
		2) CREATE TABLE tracker(
		id smallint UNSIGNED AUTO_INCREMENT,
		name VARCHAR(60),
		age TINYINT UNSIGNED,
		birth_date DATE,
		countdown_days SMALLINT UNSIGNED,
		next_birthday_date DATE,
		PRIMARY KEY (id)
		);
```

## Images of project

![Sort options](/project_images/sortOptions.png)

![Edit page](/project_images/edit.png)

![Image of MVC pattern structure](/project_images/MVC.png)

![Login](/project_images/login.png)

![Register](/project_images/register.png)

