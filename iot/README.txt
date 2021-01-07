Start Traefik and the whole System (Broker, ...) including the AIWakeUp (should already get started with the rest of the system through the docker-compose)

To start the IoT AI, run the following script:
./startAI.sh

This script expects a folder as exported by the 'save_models' function after training modells to be at the path '/home/iot-team/iotai/model'. Is this not the case, move the model to that path or change it in the script.

To start the IoT AI Development Environment, a Jupyter Lab server, run the following script:
./startDevEnv.sh

This script tries to open the folder '/home/iot-team/iotai/source' for online development. If the source files are at a different path, move them to this path or change it in the script.
