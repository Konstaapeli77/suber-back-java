# suber-back-java
Suber java backend

## Docker commands
To build and run docker use these commands:
docker build -t springio/gs-spring-boot-docker .
docker run -p 8080:8080 springio/gs-spring-boot-docker



## Windows path changes

Change global PATH:
Taken away
C:\Program Files\Common Files\Oracle\Java\javapath
C:\Program Files (x86)\Common Files\Oracle\Java\javapath

Set %JAVA_HOME%\bin in global and user PATHs.

## Github Actions

- name: Build and push Docker images
  uses: docker/build-push-action@v3.2.0

- name: Setup Java JDK
  uses: actions/setup-java@v3.8.0
    with:
      java-version: 11
      distribution: 'adopt'

## jää pois

- name: Setup Maven
  uses: stCarolas/setup-maven@v.4.5

## pom.xml

mikko@GORILLA MINGW64 /c/projects/suber-back-java (main)
$ git add .
warning: in the working copy of 'pom.xml', LF will be replaced by CRLF the next time Git touches it
