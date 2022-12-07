# suber-back-java
This project includes Super - Services Uber applications backend written in Java.

Project is integrated to Github Actions which will run after each git push.

Project has also docker capabilities.

## Docker commands

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
   