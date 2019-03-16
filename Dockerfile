#FROM alpine/git as git
#WORKDIR /app
#RUN git clone https://github.com/deas/k8s-jee-test.git
#FROM maven:3.5-jdk-8-alpine as builder
#WORKDIR /app
#COPY --from=git /app/k8s-jee-test /app
#RUN mvn install
# FROM maven:3.5-jdk-8-alpine as builder
FROM busybox
LABEL vendor="Contentreich" \
      maintainer="Andreas Steffan <a.steffan@contentreich.de>" \
      description="Kubernetes JEE Test Webapp" \
      version="1.0" \
      de.contentreich.maturity="beta"
# COPY --from=builder /app/k8s-jee-test/target/k8s-jee-test.war /k8s-jee-test.war
COPY ./target/k8s-jee-test.war /k8s-jee-test.war