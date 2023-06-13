# restful-web-services

Spring REST API

- Build a REST API for a Social Media Application
    - Design and Build a Great REST API
        - Choosing the right UR for resources (/users,/user/{id},user/{id}/posts)
        - Choosing the right request method for actions (GET, POST, PUT, DELETE,..)
        - Designing Request and response structures
        - Implementing Security, Validation and Exception Handling
    - Build Advanced REST API Features
        - Internationalisation, HATEOAS, Versioning, Documentation, content Negotiation,..
- Connect your REST API to a Database
    - Fundamentals of JPA and Hibernate


Use @ResController instead of @Controller

Use @GetMapping instead of @RequestMapping

- How are our requests handled? 
    - DispatcherServlet - Front Controller Pattern
        - Mapping servlets: dispatcherServlet urls=[/] 
        - Auto Configuration (DispatcherServletAutoConfiguration)
- How does HelloWorldBean object get converted to JSON? 
    - @ResponseBody + JacksonHttpMessageConverters Auto Configuration (JacksonHttpMessageConvertersConfiguration) 
- Who is configuring error mapping?
    - Auto Configuration (ErrorMvcAutoConfiguration) 
- How are all jars available(Spring, Spring MVC, Jackson, Tomcat)? 
    - Starter Projects - Spring Boot Starter Web (spring-webmvc, spring-web, spring- boot-starter-tomcat, spring-boot-starter-json) 


- Request Methods for REST API 
    - GET - Retrieve details of a resource 
    - POST - Create a new resource
    - PUT - Update an existing resource 
    - PATCH - Update part of a resource 
    - DELETE - Delete a resource 

- Social Media Application - Resources & Methods 
    - Users REST API 
        - Retrieve all Users 
            - GET /users 
        - Create a User 
            - POST /users 
        - Retrieve one User 
            - GET /users/{id} -> /users/1 
        - Delete a User 
            - DELETE /users/{id} -> /users/1 
    - Posts REST API 
        - Retrieve all posts for a User 
            - GET /users/{id}/posts 
        - Create a post for a User 
            - POST /users/{id}/posts 
        - Retrieve details of a post 
            - GET /users/{id}/posts/{post_id} 


DAO - Data Access Object.

Talend API Tester - for testing rest APIs


- Response Status for REST API 
    - Return the correct response status 
        - Resource is not found => 404 
        - Server exception => 500 
        - Validation error => 400 
- Important Response Statuses 200 — Success 
    - 201 — Created
    - 204 — No Content
    - 401 — Unauthorized (when authorization fails) 
    - 400 — Bad Request (such as validation error) 
    - 404 — Resource Not Found
    - 500 — Server Error 

If we want to return a specific URL of a created resource, we have to make use of a header called location

Customising Exception is a good idea if we want to maintain uniform exception of through out the project or organisation

Having the right Error structure and right Error response is very important for REST APIs.


- Advanced REST API Features 
    - Documentation
    - Content Negotiation 
    - Internationalization - i18n 
    - Versioning
    - HATEOAS
    - Static Filtering
    - Dynamic Filtering Monitoring
    - .... 

- REST API Documentation
    - Your REST API consumers need to understand your REST API: Resources 
        - Actions
        - Request/Response Structure (Constraints/Validations) 
- Challenges:
    - Accuracy: How do you ensure that your documentation is upto date and correct
    - Consistency: You might have 100s of REST API in an enterprise. How do you ensure consistency? 
- Options:
- 1: Manually Maintain Documentation 
        - Additional effort to keep it in sync with code 
- 2: Generate from code 

- REST API Documentation specification
    - Swagger - first specification
    - Open API - based on swagger specification
        - Swagger Tools ex : Swagger UI,  is still used.
    - Open API Specification : Standard, Language-agnostic interface
        - Discover and understand REST API
        - Earlier called swagger Specification
    - Swagger UI: Visualise and Interact with your REST API.
        - Can be generated from your OpenAPI Specification 

This is the working dependency of springdoc-openapi for spring-boot-3, only adding this dependency will work no other dependency is required.

* 		<dependency>
* 		<groupId>org.springdoc</groupId>
* 		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
* 		<version>2.0.4</version>
* 		</dependency>


- Content Negotiation 
- Same Resource - Same URI 
    - HOWEVER Different Representations are possible 
    - Example: Different Content Type - XML or JSON or .. 
    - Example: Different Language - English or Dutch or .. 
- How can a consumer tell the REST API provider what they want? 
    - Content Negotiation 
- Example: Accept header (MIME types - application/xml, application/json, ..) 
- Example: Accept-Language header (en, nl, fr, ..) 

This dependency is used to send the response in xml format. Client can add Accept header: application/xml to get the response in xml format.

<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
	<artifactId>jackson-dataformat-xml</artifactId>
</dependency>


- Internationalization - i18n
    - Your REST API might have consumers from around the world
    - How do you customize it to users around the world? 
        - Internationalization - i18n
    - Typically HTTP Request Header - Accept- Language is used
    - Accept-Language - indicates natural language and locale that the consumer prefers 
    - Example: en - English (Good Morning) 
    - Example: nl - Dutch (Goedemorgen) 
    - Example: fr - French (Bonjour) Example: de - Deutsch (Guten Morgen)

- Define the below variations and write code for pickitup - Standard way to do this is define messages.properties file in the same path as application.properties file.

    - Example: en - English (Good Morning) 
    - Example: nl - Dutch (Goedemorgen) 
    - Example: fr - French (Bonjour) Example: de - Deutsch (Guten Morgen)

REST API versioning types

URLs
URI Versioning - Used by Twitter
* V1: http://localhost:8080/v1/person
    * @GetMapping("/v1/person")
* V2: http://localhost:8080/v2/person
    * @GetMapping("/v2/person")
Request Param Versioning - Used by Amazon
* V1: http://localhost:8080/person?version=1
    * @GetMapping(path = "/person", params = "version=1")
* V2: http://localhost:8080/person?version=2
    * @GetMapping(path = "/person", params = "version=2")
Header Versioning - Microsoft
* V1: http://localhost:8080/person/header
    * HEADER - X-API-VERSION:1
    * @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
* V2: http://localhost:8080/person/header
    * HEADER - X-API-VERSION:2
    * @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
Content Negotiation Versioning - GitHub
* V1: http://localhost:8080/person/accept
    * HEADER - Accept:application/vnd.company.app-v1+json
    * @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
* V2: http://localhost:8080/person/accept
    * HEADER - Accept:application/vnd.company.app-v2+json
    * @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")

V1 Response
{
"name": "Bob Charlie"
}
V2 Response
{
"name": {
"firstName": "Bob",
"lastName": "Charlie"
}
}

Whenever a change will break the clients we need to do versioning, A client should not be forced to change their code as well.


- Versioning REST API - Factors Factors to consider 
    -  URI Pollution - will happen when URI versioning and Request Param versioning
    - Misuse of HTTP Headers - will happen when Accept header and custom header
    - Caching - Caching happens based on url, if we are using headers then we have to be cautious about caching
    - Can we execute the request on the browser? For URI and Request Param we can execute in headers
    - API Documentation - Documentation is easy for URI and Request Param versioning.
    - Summary: No Perfect Solution 
- My Recommendations 
    - Think about versioning even before you need it! 
    - One Enterprise - One Versioning Approach 


- HATEOAS
- Hypermedia as the Engine of Application State (HATEOAS) 
- Websites allow you to:
    - See Data AND Perform Actions (using links) 
- How about enhancing your REST API to tell consumers how to perform subsequent actions? 
    - HATEOAS 
- Implementation Options: 
    - 1: Custom Format and Implementation Difficult to maintain 
    - 2: Use Standard Implementation
        - HAL (JSON Hypertext Application Language): Simple format that gives a consistent and easy way to hyperlink between resources in your API 
        - Spring HATEOAS: Generate HAL responses with hyperlinks to resources 
- //HATEOAS Concepts
- //EntityModel
- //WebMvcLinkBuilder


Customizing REST API Responses - Filtering and more.. 
- Serialization: Convert object to stream (example: JSON) - Process of converting an object in to stream, i’e Json or xml
    - Most popular JSON Serialization in Java: Jackson 
- How about customizing the REST API response returned by Jackson framework? 
- 1: Customize field names in response 
    - @JSONProperty 
- 2: Return only selected fields 
    - Filtering 
    - Example: Filter out Passwords 
    - Two types: 
    - Static Filtering: Same filtering for a bean across different REST API @JsonIgnoreProperties, @JsonIgnore 
    - Dynamic Filtering: Customize filtering for a bean for specific REST API @JsonFilter with FilterProvider 
        - MappingJacksonValue - for dynamic filtering in REST API

- Get Production-ready with Spring Boot Actuator 
    - Spring Boot Actuator: Provides Spring Boot’s production- ready features
        - Monitor and manage your application in your production 
    - Spring Boot Starter Actuator: Starter to add Spring Boot Actuator to your application 
        - spring-boot-starter-actuator 
    - Provides a number of endpoints:
        - beans - Complete list of Spring beans in your app 
        - health - Application health information metrics - Application metrics
        - mappings - Details around Request Mappings 
        - and a lot more ....... 

- Explore REST API using HAL Explorer 
    - 1: HAL (JSON Hypertext Application Language)
        - Simple format that gives a consistent and easy way to hyperlink between resources in your API 
    - 2: HAL Explorer
        - An API explorer for RESTful Hypermedia APIs using HAL 
        - Enable your non-technical teams to play with APIs 
    - 3: Spring Boot HAL Explorer
        - Auto-configures HAL Explorer for Spring Boot Projects 
        - spring-data-rest-hal-explorer 
- <dependency>
- 	<groupId>org.springframework.data</groupId>
- 	<artifactId>spring-data-rest-hal-explorer</artifactId>
- </dependency>


H2 database using JPA

Switch to Mysql

* 		docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle


Data.sql files will not be executed when connecting to an actual database. This files only work for in memory database.

//Use this to disable the spring security while developing in the Application class.
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
        )


Spring security

1. Intercepts the request
2. Does a filter chain
3. Checks All requests should be authenticated.
4. If a request is not authenticated, a web page is shown
5. CSRF -> POST, PUT, will be impacted

In spring security if we need to override the spring security config, we have to define it again.



Some of the important response statuses 1) 200 — Success 2) 201 — Created 3) 204 — No Content 4) 401 — Unauthorized (when authorization fails) 5) 400 — Bad Request (such as validation error) 6) 404 — Resource Not Found 7) 500 — Server Error







