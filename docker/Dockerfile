FROM openjdk:17-jdk-alpine

ARG MUSTANG_SERVER_VERSION

ENV MUSTANG_SERVER_VERSION=${MUSTANG_SERVER_VERSION}
# Since this is a running network service we'll create an unprivileged account
# which will be used to perform the rest of the work and run the actual service:

# Debian:
# RUN useradd --system --user-group --home-dir=/opt/verapdf-rest verapdf-rest
# Alpine / Busybox:

RUN addgroup -S mustangserver && adduser -h /opt/mustangserver -S mustangserver -G mustangserver

USER mustangserver

#USER mustangserver
WORKDIR /opt/mustangserver

COPY target/mustangserver-${MUSTANG_SERVER_VERSION}.war /opt/mustangserver/

EXPOSE 8000

ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=local /opt/mustangserver/mustangserver-${MUSTANG_SERVER_VERSION}.war"]