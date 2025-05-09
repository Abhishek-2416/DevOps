### Command to get the information about all the images , the container and the running , stopped containers
```
docker info 
```

### Command to run docker image, which shoots up a container

```
docker container run --publish 8081:80 nginx
```

- To make it just run it in the background which frees up the terminal also

```
docker container run --publish 8081:80 --detach nginx
```

### Command to stop the container which is running

```
docker container stop <container id/name>
```

### Comand to list all our running containers

```
docker container ls
```

- Command to list all the container (stopped also)

```
docker container ls -a
```

### Command to set name for the container

```
docker container run --publish 8081:80 --detach --name nginx-container nginx
```

### Command to check the logs of a container
```
docker container logs <container-name/id>
```

### Command to remove container, it removes it from the list of all container
```
docker container rm <container-name/id>
```

---

# What's going on inside of a Container

### Commnad to list all the processes in one container
```
docker container top <container-name>
```

### Command to inspect a container, how a container was started, networks and all those things
```
docker container inspect <container-name>
```

### Command to show the live performance data for all containers
```
docker container stats
```

### Command to get the bash shell inside of the container

- Containers are isolated user-space environments that share the host's Linux kernel. They are not full virtual machines but run as isolated processes. To access a shell inside a running container, we can run an interactive shell session like this: 

```
docker container run -it --name proxy nginx bash
```

- If the container is not running presently then
```
docker container restart <container-name>
docker exec -it <container-name> bash
```

---

# Docker Networks

### To show all networks
```
docker network ls
```

### To inspect a network 
```
docker network inspect
```
