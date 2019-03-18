FROM maven:3.5-jdk-8-alpine as builder
COPY . /build
WORKDIR /build
RUN mvn package
FROM busybox
LABEL vendor="Contentreich" \
      maintainer="Andreas Steffan <a.steffan@contentreich.de>" \
      description="Kubernetes JEE Test Webapp" \
      version="1.0" \
      de.contentreich.maturity="beta"
COPY --from=builder /build/target/k8s-jee-test.war /k8s-jee-test.war
# COPY ./target/k8s-jee-test.war /k8s-jee-test.war