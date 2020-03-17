package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends Client.SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null && message.contains(":")) {
                String[] line = message.split(":");
                String user = line[0];
                String command = line[1].trim();
                String text;
                SimpleDateFormat formatter;

                switch (command) {
                    case "дата":
                        formatter = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        formatter = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        formatter = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        formatter = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        formatter = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        formatter = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        formatter = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        formatter = new SimpleDateFormat("s");
                        break;
                    default:
                        return;
                }
                text = String.format("Информация для %s: %s", user, formatter.format(Calendar.getInstance().getTime()));
                sendTextMessage(text);
            }
        }
    }
}
