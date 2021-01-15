import argparse
import asyncio
import json
import logging
import os
import platform
import ssl
import re

import socket
import time
from typing import MutableSet

import aiohttp_cors
from aiohttp import web

from aiortc import RTCPeerConnection, RTCSessionDescription
from aiortc.contrib.media import MediaPlayer
import paho.mqtt.client as mqtt

ROOT = os.path.dirname(__file__)

options = {"framerate": "30", "video_size": "640x480"}
player = MediaPlayer("/dev/video0", format="v4l2", options=options)
port = 8080

async def new_pc(offer) -> RTCPeerConnection:
    pc = RTCPeerConnection()
    await pc.setRemoteDescription(offer)
    pc.addTrack(player.video)

    answer = await pc.createAnswer()
    await pc.setLocalDescription(answer)
    return pc


async def offer(request):
    params = await request.json()
    offer = RTCSessionDescription(sdp=params["sdp"], type=params["type"])

    pc = await new_pc(offer)
    pcs.add(pc)

    @pc.on("iceconnectionstatechange")
    async def on_iceconnectionstatechange():
        print("ICE connection state is %s" % pc.iceConnectionState)
        if pc.iceConnectionState == "failed":
            await pc.close()

    return web.Response(
        content_type="application/json",
        text=json.dumps(
            {"sdp": pc.localDescription.sdp, "type": pc.localDescription.type}
        ),
    )


pcs: MutableSet[RTCPeerConnection] = set()


async def on_shutdown(app):
    # close peer connections
    coros = [pc.close() for pc in pcs]
    await asyncio.gather(*coros)
    pcs.clear()
    pass


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
    client.connect('vm103.htl-leonding.ac.at', port=1883)
    client.on_publish = on_publish

    IPAddr = IPAddr + ":" + str(port)
    publish("htlleonding/firstfloor/e581/webcam/data", IPAddr)
    print("published ip Addr %s" % (IPAddr))


if __name__ == "__main__":
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

    resource = cors.add(app.router.add_resource("/offer"))
    route = cors.add(resource.add_route("POST", offer))

    web.run_app(app, port=port)
