
# How to run the project in minikube

###
### Step 1: Build the local image and push on Docker Hub

```
docker build . -t dactien020796/spring-reactive-sample 
```

```
docker push dactien020796/spring-reactive-sample 
```
###
### Step 2: Use kompose (https://kompose.io/) to auto-convert docker compose to Kubernetes
```
kompose convert 
```
All the deployments and services needed will be auto generated

###
### Step 3: Apply those file on Kubernetes
```
kubectl apply -f db-service.yaml,server-service.yaml,db-deployment.yaml,server-deployment.yaml
```

Minikube doesn’t support LoadBalancer services, so the service will never get an external IP. But you can access the service anyway through its external port by running
```
minikube service server --url
```

