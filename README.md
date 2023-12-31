# Build Container
```
docker build -t smotastic/bla .
```

# Run Container
```
docker run smotastic/bla
```

# Deployment
```bash
mvn clean install
scp target/*.jar pi@raspberrypi:~/dev/smarthome/app.jar
# kill any running services
ssh pi@raspberrypi 'fuser -k 8080/tcp'
ssh pi@raspberrypi 'java -jar ~/dev/smarthome/app.jar'
```

# Nice to have
## Turn down volume
`sudo amixer cset numid=1 80%`

## Check used ports
`sudo lsof -i -P -n | grep LISTEN`