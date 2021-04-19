#!/bin/sh

docker run --rm -p 8080:8080 -e DB_VENDOR=h2 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin \
    -e KEYCLOAK_IMPORT=/tmp/keycloak_realm_workshop.json \
    -v $(pwd)/keycloak_realm_workshop.json:/tmp/keycloak_realm_workshop.json jboss/keycloak:12.0.4
