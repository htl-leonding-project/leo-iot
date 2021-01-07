import json
import socket
import ssl
import time
import os

import aiohttp_cors

import paho.mqtt.client as mqtt
import requests
from aiohttp import web

port = 8081
count = 0
room = "e581"
mqtt_url = 'vm103.htl-leonding.ac.at'
cert_path ='cert.pem'
key_path = 'key_cert.pem'

def publish_ip_on_mqtt():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        s.connect(('10.255.255.255', 1))
        IPAddr = s.getsockname()[0]
    except:
        IPAddr = '127.0.0.1'
    finally:
        s.close()


    client = mqtt.Client('527e56ce-f7d8-4089-bc37-b3c92f1e768c')
    client.username_pw_set('4bhif', '8dhj45ug')

    def on_connect(client1, userdata, flags, rc):
        if rc == 0:
            print("connected ok")
        else:
            print(rc)

    def on_publish(client, userdata, result):
        print(result + client + userdata)

    def publish(link, value):
        ret = client.publish(str(link),
                             json.dumps({'timestamp': time.time(), 'value': value}, separators=(',', ':')),
                             retain=True, qos=0)
        return ret

    client.on_connect = on_connect
    client.connect(mqtt_url, port=1883)
    client.on_publish = on_publish

    IPAddr = IPAddr + ":" + str(port)
    publish("htlleonding/firstfloor/" + room + "/pc/webcam", IPAddr)
    print("published ip Addr %s" % (IPAddr))


def start_motion():
    print("started motion")
    os.system("motion start")


def stop_motion():
    print("stopped motion")
    os.system("pkill motion")


async def start(req):
    global count
    if count == 0:
        start_motion()

    count += 1
    print(count)
    return web.Response(
        content_type="application/json",
        text=json.dumps(
            {"count": count}
        ),
    )


async def stop(req):
    global count
    count -= 1
    if count <= 0:
        stop_motion()
        count = 0

    print(count)
    return web.Response(
        content_type="application/json",
        text=json.dumps(
            {"count": count}
        ),
    )


async def on_shutdown(app):
    pass


if __name__ == '__main__':

    publish_ip_on_mqtt()
    app = web.Application()

    app.on_shutdown.append(on_shutdown)

    cors = aiohttp_cors.setup(app, defaults={
        "*": aiohttp_cors.ResourceOptions(
            allow_credentials=False,
            expose_headers="*",
            allow_headers="*",
        )
    })

    for res in list(app.router.resources()):
        cors.add(res)

    resource = cors.add(app.router.add_resource("/start"))
    route = cors.add(resource.add_route("GET", start))

    resource = cors.add(app.router.add_resource("/stop"))
    route = cors.add(resource.add_route("GET", stop))

    # openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365
    # openssl pkey -in key.pem -out key_cert.pem

    ssl_context = ssl.create_default_context(ssl.Purpose.CLIENT_AUTH)
    ssl_context.load_cert_chain(cert_path, key_path)

    web.run_app(app, port=port, ssl_context=ssl_context)
