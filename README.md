# suber-back-java
This project includes Super - Services Uber applications backend written in Java.

Project is integrated to Github Actions which will run after each git push.

Project has also docker capabilities.

## Docker commands

Launch Postgres container:
```
docker run -p 5432:5432 --name suber-postgres -e POSTGRES_PASSWORD=password -d postgres
docker run -p 5432:5432 --name suber-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dev -d postgres
```

To build and run docker use these commands:
```
docker build -t springio/gs-spring-boot-docker .
docker run -p 8080:8080 springio/gs-spring-boot-docker
```

## Docker build

docker:
  needs: sonar
  name: Push to Docker Hub
  runs-on: ubuntu-latest
  steps:
    - name: Checkout
      uses: actions/checkout@v2.5.0

    - name: Setup Java JDK
      uses: actions/setup-java@v3.8.0
      with:
        java-version: '11'
        distribution: 'adopt'

    - name: Cache local Maven repository
      uses: actions/cache@v3
      with:
        path: ~/.m2/repository
        key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
        restore-keys: |
          ${{ runner.os }}-maven-

    - name: Run Tests
      run: mvn -B package -Dspring.profiles.active=test

    - name: Set up QEMU
      uses: docker/setup-qemu-action@v2

    - name: Set up Docker Buildx
      uses: docker/setup-buildx-action@v2

    - name: Login to Docker Hub
      uses: docker/login-action@v2
      with:
        username: ${{ secrets.DOCKERHUB_USERNAME }}
        password: ${{ secrets.DOCKERHUB_TOKEN }}

    - name: Build and push
      uses: docker/build-push-action@v3
      with:
        push: true
        tags: user/app:latest
   