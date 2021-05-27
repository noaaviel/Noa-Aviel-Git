#!/usr/bin/env python3
import socket

HOST = 'localhost'  # The server's hostname or IP address
PORT = 8888        # The port used by the server

socketing = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


def send_msg(msg):
        msg_to_send = bytearray(str.encode(msg + '$'))
        socketing.sendall(msg_to_send)


def connect():
    socketing.connect((HOST, PORT))