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
cd radioalarm
#mvn clean install
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

## Add scripts to autostart
```shell
sudo nano /etc/init.d/radiocontroller
# Add contents to script
sudo chmod +x /etc/init.d/radiocontroller
sudo update-rc.d radiocontroller defaults
# Removing it
# sudo update-rc.d  -f radiocontroller remove
```

Add contents to script
```shell
#! /bin/sh
### BEGIN INIT INFO
# Provides: MeinProgramm
# Required-Start: 
# Required-Stop: 
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Short-Description: Starts & Stops Radio
# Description: Starts & Stops Radio
### END INIT INFO
 
case "$1" in
 start)
 java -jar /home/pi/dev/smarthome/app.jar > /dev/null &
 ;;
 
 stop)
 echo "Stopp"
 fuser -k 8080/tcp
 ;;
 
 restart)
 echo "Restart"
 ;;
 *)
 echo "else"
 ;;
esac

exit 0
```