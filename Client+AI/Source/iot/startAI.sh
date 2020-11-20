docker stop iotai
docker rm iotai
docker container run -d --name=iotai --gpus all --network=traefikNetwork --env TF_FORCE_GPU_ALLOW_GROWTH=true -v /home/iot-team/iotai/model:/model -v /etc/hosts:/etc/hosts registry.gitlab.com/sdanninger/iot/mqttai:1.0.0
