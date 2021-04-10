# Spring Security for Newbies

This repository contains demo labs to show how spring security works using a simple spring boot application.

## The demo application

The application provides the following REST APIs:

* [localhost:9090/api/message (GET request)](http://localhost:9090/api/message)
* localhost:9090/api/message (POST request)
* [localhost:9090/api/admin (GET request)](http://localhost:9090/api/admin)

In addition, it exposes all common spring actuator REST APIs:

* [localhost:9090/actuator](http://localhost:9090/actuator)

You can also look at the REST API docs using:

* [Open API V3](http://localhost:9090/v3/api-docs)
* [Swagger UI](http://localhost:9090/swagger-ui.html)

You may also use the provided [postman collection](Spring%20Security%20For%20Newbies.postman_collection.json) for your convenience.

## The labs

All labs are organized using different git branches:

1. Initial unsecured application (branch _main_)
2. Using spring security just with spring boot auto-configuration (branch _autoconfig_)
3. Custom authentication configuration with our own user storage (branch _custom-auth_) 
4. Add authorization to the application (branch _authorization_)
5. Convert app into an OAuth/OpenID Connect resource server (branch _oauth_)
