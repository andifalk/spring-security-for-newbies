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

## Lab 1: Spring Security Auto-Configuration

Just by adding the spring boot starter dependency for spring security (_org.springframework.boot:spring-boot-starter-security_)
the following secure by default features are enabled:

* All endpoints require authentication (except the _health_ and _info_ actuator endpoints) giving a _401 Unauthorized_ error
* By default, the authentication is possible using basic authentication and form based user/password authentication
* A default user _user_ with an auto-generated password is configured (see console log for the password)
* Session-fixation protection
* CSRF protection for POST and PUT requests
* Recommended response headers for security
    * X-Content-Type-Options: nosniff
    * X-XSS-Protection: 1; mode=block
    * X-Frame-Options: DENY
    * Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    * Pragma: no-cache

Please note that the original tests would break with 401 errors (due to missing authentication), and an 403 error (for the post request due to missing CSRF token). So the tests have to be extended for authentication and CSRF support as well. 

## Next step

In lab 2 we will customize the auto-configured authentication and user (to get rid of the auto generated password on the console).