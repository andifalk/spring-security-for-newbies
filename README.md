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

## Lab 2: Spring Security Custom Authentication Configuration

In this lab the previously auto-configured security is replaced by
explicit security configuration:

* All endpoints still require authentication (except the _health_ and _info_ actuator endpoints) giving a _401 Unauthorized_ error
* The authentication is possible using basic authentication and form based user/password authentication
* Two users are configured in an in-memory store:
  * A standard user with username _user_ and password _secret_ (role _USER_)
  * An admin user with username _admin_ and password _admin_ (roles _USER_ and _ADMIN_)
* All other default protections for session-fixation and csrf are still active and recommended response headers are still set

The tests do not require any change as these do not use the real users and still run with the existing user specified. 

## Next step

In lab 3 we will add authorization on the web and method layers.