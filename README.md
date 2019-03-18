# Test Tomcat on Kubernetes
- Graceful Termination
- Deployment Scaling
- Load Balancing (via Service and Ingress Controller)
- Health Checks

## Installation
```
helm install --name k8s-jee-test --namespace k8s-jee-test chart
```

You may want to check `charts/values.yaml` and tweak values.


## Testing
Example:
```
ab -n 10 http://ip-and-port-of-service/k8s-jee-test/\?passthrough\=foobar\&sleep\=10
```

The `k8s-jee-test` reference application should be logging meaningful infomartion to stdout.

## Request parameters

Request Parameter               | Description                           
------------------------------- | ------------------------------------- |
`sleep`                         | Millis to wait before app responds    |
`passthrough`                   | passthrough value (for tracing)       |
`set_healthy`                   | `true` or `false`. Setting `false` gives `500` status. Restart should be kicking in.|                        |

The `k8s-jee-test` reference app has as `unloadDelay` set to `20000`.