FROM registry.saas.hand-china.com/hap-cloud/base:latest

WORKDIR /

COPY ./Exam1/target/test3-jar-with-dependencies.jar /JavaTest3.jar

CMD ["java", "-jar", "JavaTest3.jar"]