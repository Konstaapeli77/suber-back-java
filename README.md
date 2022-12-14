# suber-back-java
This project includes Super - Services Uber applications backend written in Java.

Project is integrated to Github Actions which will run after each git push.

Project has also docker capabilities.

Run application with:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=dev
```
## Environment variables

- **dev** development time usage (uses postgres dev-database)
- **prod** usage inside docker-compose and in production (uses postgres prod-database)
- **local** ment for conainer-only usage (uses H2 database)
- **tests** ment for development time testing purposes (uses H2 database)

## Docker commands

Launch Postgres container:
```
docker run -p 5432:5432 --name suber-postgres -e POSTGRES_PASSWORD=password -d postgres
docker run -p 5432:5432 --name suber-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dev -d postgres
```


To build and run konstaapeli77 docker use these commands:
``` 
docker run --env ENV=local -p 8080:8080 konstaapeli77/suber 
```
## Maven tests with Jacoco coverage

``` 
mvn test -Dspring.profiles.active=test -f pom.xml
``` 

## Docker push

```
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
```

## Sonar

```
  sonar:
    needs: tests
    name: SonarCloud analysis
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v2.5.0

      - name: Setup Java JDK
        uses: actions/setup-java@v3.8.0
        with:
          java-version: '11'
          distribution: 'adopt'

      - name: Cache SonarCloud packages
        uses: actions/cache@v1
        with:
          path: ~/.sonar/cache
          key: ${{ runner.os }}-sonar
          restore-keys: ${{ runner.os }}-sonar

      - name: Cache local Maven repository
        uses: actions/cache@v3
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-

      - name: Build and analyze
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Konstaapeli77_suber-back-java -Dspring.profiles.active=test```
```
## Deploy jar to Github

```
  publish:
    needs: tests
    name: JAR Publishing to GitHub packages
    runs-on: ubuntu-latest
    permissions:
      contents: read
      packages: write
    steps:
      - name: Checkout
        uses: actions/checkout@v2.5.0

      - name: Setup Java
        uses: actions/setup-java@v3.8.0
        with:
          java-version: '11'
          distribution: 'adopt'

      - name: Publish package
        run: mvn --batch-mode deploy -Dspring.profiles.active=test
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```