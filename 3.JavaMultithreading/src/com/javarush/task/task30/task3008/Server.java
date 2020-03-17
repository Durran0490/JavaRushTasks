package com.javarush.task.task30.task3008;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(ConsoleHelper.readInt());
            System.out.println("сервер запущен");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                Thread thread = new Handler(serverSocket.accept());
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    public static void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> e : connectionMap.entrySet()) {
                e.getValue().send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("ERROR while sending message");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message answer;
            String name;
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                answer = connection.receive();
                name = answer.getData();
            } while (!(answer.getType().equals(MessageType.USER_NAME)
                    && name != null && !name.equals("")
                    && !connectionMap.containsKey(name)));

            connectionMap.put(answer.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED, "Name Accepted - It's all cool man!"));
            return answer.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> map : connectionMap.entrySet()) {
                if (!(map.getKey().equals(userName))) {
                    connection.send(new Message(MessageType.USER_ADDED, map.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String text = userName + ":" + " " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage(
                            String.format("Message type error: %s, from user: %s",
                                    message.getType(), userName));
                }
            }
        }

        public void run() {
            Connection connection = null;
            String name = null;
            ConsoleHelper.writeMessage("Connection to: [" + socket.getRemoteSocketAddress().toString() + "] established");
            try {
                connection = new Connection(socket);
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                notifyUsers(connection, name);
                serverMainLoop(connection, name);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом.");
            }
            if(name != null) {
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            }
            ConsoleHelper.writeMessage("Connection is cosed!");
        }

    }
}
