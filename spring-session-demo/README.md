# spring-session-demo
Demo SpringSession with Redis, using [docker swarm](https://docs.docker.com/get-started/part4/#create-a-cluster) for load balancer

### Create docker swarm
```
On Windows:
$ docker-machine create -d virtualbox myvm1           # Create new VM in docker
$ docker swarm init --advertise-addr 192.168.99.100   # Create swarm

## After run above command, we get a command to add worker to swarm (printed to screen). Let run it

$ docker-machine ssh myvm1 "docker swarm join --token SWMTKN-1-1j2tpjx1ue244rvccd5w12wpgayymiq4nhc1xtoefnsmiqy7iw-ccb2je9thhwq5rdcg6vueubm6 192.168.99.100:2377"
```

### Start application
```
# Deploy on swarm
$ docker stack deploy -c docker-compose.yml mywebapp
```

### Test
```
192.168.99.100:8080
```
