# Cache Demo

Simple Spring Boot project with Infinispan in-memory Data Grid. Used as embedded cache for local development, remote cache for _"production"_.

_**Warning:** Demo project. Not intended to use in production!_

## Cache

We're using Infinispan as simple **read-through cache** here. The endpoint `/tasks` lists all the tasks. The endpoint `/tasks/<id>` simulates slow loading from database. After the first call, the result is in cache and subsequent responses are returned from cache (very fast).

Infinispan also supports _write-through_ and _write-behind_. These are not demonstrated here.

## Usage

Swagger-UI can be found in path `/swagger-ui/index.html` and allows the interaction with the endpoints. Or use cURL or httpie... All endpoints are under `/tasks`.

Spring Boot Actuator gives info about health and environment.

## Install on OpenShift

1. Create namespace "cache-demo"
2. Install Red Hat Data Grid operator in the namespace
3. Create Infinispan cluster via the provided file (infinispan-cluster.yml). No authentication and no encryption here - demo use only!!
4. Create the application via s2i: 

`oc new-app java:11~https://github.com/nikolaus-lemberski/cachedemo.git --env=SPRING_PROFILES_ACTIVE=prod`
5. Expose service: `oc expose svc cachedemo`
