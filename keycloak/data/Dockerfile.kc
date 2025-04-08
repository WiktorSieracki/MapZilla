FROM quay.io/keycloak/keycloak:26.1.0

COPY realm-export.json /opt/keycloak/data/import/

CMD [ "--verbose", "start-dev", "--import-realm" ]
