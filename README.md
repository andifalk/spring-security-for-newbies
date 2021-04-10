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

## Lab 4: Convert application into an OAuth 2.0 & OpenID Connect Compliant Resource Server

In this lab the security configuration is changed for authentication using JWT  as bearer tokens:

* All endpoints now require a JWT signed by keycloak
* The same authorizations are in place than in the previous lab

To perform this lab you need to set up and start a keycloak instance first.
Please see the [setup section](setup) for more details.

By default, spring security maps all contents of JWT _scope_ claim to respective spring security authorities like _SCOPE_user_ or _SCOPE_admin_.
As this would not fit our currently configured authorization using _ROLE_ADMIN_ and _ROLE_USER_ there is a requirement for some conversion.
Luckily spring security provides a way to do this, see class _JwtAuthoritiesConverter_ on how to do it.

### Get valid JWT

To get valid tokens for accessing the REST APIs the following client configuration at keycloak has to be used:

* Client ID: library-client
* Client Secret: 9584640c-3804-4dcd-997b-93593cfb9ea7
* Authorization Endpoint: http://localhost:8080/auth/realms/workshop/protocol/openid-connect/auth
* Token Endpoint: http://localhost:8080/auth/realms/workshop/protocol/openid-connect/token
* Redirect URI: http://localhost:9090/library-client/login/oauth2/code/keycloak

#### Users and Roles

The configured users to login via keycloak as identity provider are the following ones:

| Username | Email                    | Password | Role            |
| ---------| ------------------------ | -------- | --------------- |
| bwayne   | bruce.wayne@example.com  | wayne    | USER            |
| ckent    | clark.kent@example.com   | kent     | ADMIN           |

#### Use The Password Grant Flow

```shell
http --form http://localhost:8080/auth/realms/workshop/protocol/openid-connect/token grant_type=password \
username=ckent password=kent client_id=library-client client_secret=9584640c-3804-4dcd-997b-93593cfb9ea7
``` 

### Access the REST APIs

To access the REST APIs the JWT has to be presented as bearer token in the authorization header.

```
http localhost:9090/api/message 'Authorization: Bearer <JWT>'
```

The tests also require some changes to reflect the new authorizations. 

This concludes the labs for introducing spring security.