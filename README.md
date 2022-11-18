# Spring Boot REST API

<div id="top"></div>
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/ioanat94/springboot-rest-api">
    <img src="https://miro.medium.com/max/856/1*O68LbDvD5Dcsnez73M7v4Q.png" alt="Logo" width="120" height="auto">
  </a>
<h3 align="center">Spring Boot REST API</h3>

  <p align="center">
    An e-commerce REST API with Java and Spring Boot.
    <br />
    <br />
    <a href="https://documenter.getpostman.com/view/21218217/2s8YmNR3hG">View documentation</a>
    <br />
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#built-with">Built With</a></li>
    </li>
    <li>
      <a href="#installation">Installation</a></li>
    </li>
    <li><a href="#features">Features</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

### Built With

* [Java](https://www.java.com/en/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [JWT](https://jwt.io/)
* [PostgreSQL](https://www.postgresql.org/)

<p align="right">(<a href="#top">back to top</a>)</p>

### Installation

To get a local copy up and running follow these simple steps.

1. Clone the repo  
   <br /> 
   ```sh
   git clone https://github.com/ioanat94/springboot-rest-api
   ```
2. Create an `application.properties` file in the `src/main/resources` directory to set up your database and Google Auth credentials  
   <br /> 
   ```sh
   spring.datasource.url=<your database url>
   spring.datasource.username=<your database username>
   spring.datasource.password=<your database password>
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.properties.hibernate.globally_quoted_identifiers=true
   server.error.include-message=always
   spring.security.oauth2.client.registration.google.client-id=<your Google client ID>
   spring.security.oauth2.client.registration.google.client-secret=<your Google client secret>
   ```
3. Create a `.env` file to store your JWT secret  
   <br /> 
   ```sh
   JWT_SECRET=<lots of random letters and numbers>
   ```
4. Start the application!

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Features

- Table schemas for Product, Order, User and Admin
- References between tables (Many to One, Many to Many)
- CRUD routes for all models
- Request validation
- Error handling
- Google authentication for users
- Local authentication for admins
- JWT generation (access token, refresh token)
- Protected routes
- Permission-based route access
- Sorting
- Filtering
- Pagination

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Ioana Tiplea - ioanatiplea94@gmail.com  - [LinkedIn](https://www.linkedin.com/in/ioana-tiplea/)

Project Link: [https://github.com/ioanat94/springboot-rest-api](https://github.com/ioanat94/springboot-rest-api)

<p align="right">(<a href="#top">back to top</a>)</p>
