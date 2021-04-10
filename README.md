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

## Lab 3: Spring Security Authorization On Web And Method Layers

In this lab the security configuration is extended for authorization:

* The endpoint _/api/admin/_ and all secured actuator endpoints require the _ADMIN_ role
* All other endpoints work using the _USER_ role  
* The authentication is possible using basic authentication and form based user/password authentication
* Two users are configured in an in-memory store:
  * A standard user with username _user_ and password _secret_ (role _USER_)
  * An admin user with username _admin_ and password _admin_ (roles _USER_ and _ADMIN_)
* The authorization is configured for web layer (in the _WebSecurityConfiguration_ class), and the method layer using the _@PreAuthorize_ annotation

The tests also require some changes to reflect the new authorizations. 

## Next step

In lab 4 we will convert the application into an OAuth 2.0 & OpenID Connect compliant resource server authenticating using JWT (JSON web tokens).