import socket
import struct
import sys
import select
import threading


def chat_thread():
    IP_address = sys.argv[1]
    port = int(sys.argv[2])
    list_of_sockets_fd = []
    clients = {}  # <socket_TCP : (ip, port_udp)>

    listen_for_clients = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    listen_for_clients.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    listen_for_clients.bind((IP_address, port))  # listen on every address of the server
    listen_for_clients.listen(10)

    list_of_sockets_fd.append(listen_for_clients)

    while True:
        read_fds = list_of_sockets_fd
        ready_to_read, _, _ = select.select(read_fds, [], [])

        for fd in ready_to_read:
            if fd == listen_for_clients:  # a new client tries to connect
                client_socket, client_address = listen_for_clients.accept()

                # send him the ip and udp port of the other clients
                client_socket.sendall(struct.pack("!I", len(clients)))

                for other_client_socket in clients:
                    other_client = clients[other_client_socket]
                    client_socket.sendall(other_client[0])  # send the ip address
                    client_socket.sendall(struct.pack("!H", other_client[1]))

                # receive the client's udp port and store it in a pair
                udp_port = struct.unpack("!H", client_socket.recv(2))[0]
                new_client = (socket.inet_aton(client_address[0]), udp_port)  #  inet_aton converts from readable ip adress to 32 bits
                print("New client connected:" + client_address[0] + ":" + str(udp_port))

                # send his data to all the other clients
                for other_client_socket in clients:
                    other_client_socket.send(b'N')  # N for new client
                    other_client_socket.send(new_client[0])
                    other_client_socket.send(struct.pack("!H", new_client[1]))

                # add the new client to the list of clients and the select list
                list_of_sockets_fd.append(client_socket)
                clients[client_socket] = new_client

            else:  # we got a message from a client, probably a quit
                operation = fd.recv(1)
                if operation == b'L':  # the operation code for leave
                    print("Client is leaving...")
                    fd.close()
                    deleted_client = clients[fd]
                    del clients[fd]
                    list_of_sockets_fd.remove(fd)

                    # tell the other clients that he is leaving
                    for other_client in clients:
                        other_client.send(b'L')
                        other_client.send(deleted_client[0])
                        other_client.send(struct.pack("!H", deleted_client[1]))

                else:
                    print("Unknown operation")


def main():
    # create a background thread using the daemon option
    threading.Thread(target=chat_thread, daemon=True).start()

    # for stopping the server
    while True:
        user_input = input()
        if user_input == "Q":
            print("Shutting down...")
            exit(0)


main()
