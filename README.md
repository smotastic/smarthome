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
# kill any running services
ssh pi@raspberrypi 'fuser -k 8080/tcp'
scp target/*.jar pi@raspberrypi:~/dev/smarthome/app.jar
ssh pi@raspberrypi 'java -jar /home/pi/dev/smarthome/app.jar > /dev/null &'
'
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

## Find Radiostations

### Create DB
```
createdb radio
createuser radiouser
psql -d radio
$ alter user radiouser with encrypted password 'qwerty';
$ grant all privileges on database radiostation to radiouser;
```

### Create Table
```postgresql
create table if not exists public.radiostation
(
    name          varchar(1000),
    url           varchar(1000),
    state         varchar(102),
    homepage      varchar(200),
    tags          varchar(1000),
    country       varchar(30),
    countrycode   varchar(2),
    language      varchar(300),
    languagecodes varchar(103),
    codec         varchar(50),
    bitrate       numeric,
    votes         numeric
);
```

### Run Script