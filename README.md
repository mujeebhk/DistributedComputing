# DistributedComputing
Sample Distributed Computing 


cd deployments
kubectl apply -f common/ns-and-sa.yaml
kubectl apply -f common/default-server-secret.yaml
kubectl apply -f common/nginx-config.yaml
kubectl apply -f rbac/rbac.yaml
kubectl apply -f deployment/nginx-ingress.yaml
kubectl get pods --namespace=nginx-ingress
kubectl apply -f service/loadbalancer-aws-elb.yaml
kubectl get svc --namespace=nginx-ingress
kubectl apply -f common/nginx-config.yaml
cd ..
kubectl apply -f muj.yaml
kubectl apply -f bye.yaml
kubectl apply -f micro-ingress.yaml


