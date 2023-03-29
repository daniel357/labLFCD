import select
import socket
import struct
import sys
import threading

def receive_messages():
    # listen for incoming messages from the server and other clients
    try:
        while True:
            sockets, _, _ = select.select([my_udp_socket, socket_server_client], [], [])
            if socket_server_client in sockets:
                operation = socket_server_client.recv(1)
                client_ip = socket.inet_ntoa(socket_server_client.recv(4))
                _port = struct.unpack("!H", socket_server_client.recv(2))[0]

                if operation == b'L':
                    print("Client "+ client_ip + ":" + str(_port) + " left the chatroom.")
                    other_clients.remove((ip_address, _port))

                elif operation == b'N':
                    print("Client " + client_ip + ":" + str(_port) + " joined the chatroom.")
                    other_clients.add((client_ip, _port))
                else:
                    print("Unknown operation received from server")

            if my_udp_socket in sockets:
                message, address = my_udp_socket.recvfrom(256)
                print("Client", address[0] + ":" + str(address[1]), "-", message.decode())
    except OSError as osError:
        socket_server_client.close()
        my_udp_socket.close()
        print("OSError:", osError.strerror)
        print("Type 'Q' to quit:")


if __name__ == "__main__":
    ip_address = sys.argv[1]
    port = int(sys.argv[2])
    socket_server_client = socket.socket()
    socket_server_client.connect((ip_address, port))

    # all clients in set
    other_clients = set()
    number_of_clients = struct.unpack("!I", socket_server_client.recv(4))[0]

    for _ in range(number_of_clients):
        client_address = socket.inet_ntoa(socket_server_client.recv(4))
        client_port = struct.unpack("!H", socket_server_client.recv(2))[0]
        other_clients.add((client_address, client_port))

    # communicate between clients with UDP
    my_udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    my_udp_socket.sendto(b'somewhere', ('7.7.7.7', 5523))

    # get the port associated to this socket and send it to the server
    # which will then send it to all other clients
    _, my_udp_port = my_udp_socket.getsockname()
    print("My UDP port is:", str(my_udp_port))
    socket_server_client.send(struct.pack("!H", my_udp_port))

    threading.Thread(target=receive_messages, daemon=True).start()

    while True:
        user_input = input()
        if user_input == "Q":
            # tell the server we are leaving
            socket_server_client.send(b'L')
            print("Leaving the chat room and shutting down")
            my_udp_socket.close()
            socket_server_client.close()
            exit(0)
        for other_client in other_clients:
            my_udp_socket.sendto(user_input.encode(), other_client)

