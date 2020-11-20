docker stop aidev
docker rm aidev
docker container run -d --gpus all --network=traefikNetwork --label "traefik.enable=true" --label "traefik.frontend.rule=Host:vm103.htl-leonding.ac.at;PathPrefix:/aidev" --label "traefik.port=8888" --name=aidev -p 8888:8888 -v /etc/hosts:/etc/hosts -v ~/iotai/source:/iotai registry.gitlab.com/sdanninger/iot/aidev:1.0.0
