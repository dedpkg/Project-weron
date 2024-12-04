package com.example.telegrammod;

import org.telegram.tdlib.TdApi;

public class TelegramHandler {
    private static TdApi.Client client;

    // Инициализация TDLib клиента
    public static void initialize() {
        client = new TdApi.Client();
        // Подключаемся к TDLib
    }

    // Получение информации о чате, включая аватар
    public static void getChatInfo(int chatId) {
        TdApi.GetChat getChat = new TdApi.GetChat();
        getChat.chatId = chatId;
        
        // Асинхронный запрос для получения информации о чате
        client.send(getChat, new TdApi.Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {
                if (object.getConstructor() == TdApi.Chat.CONSTRUCTOR) {
                    TdApi.Chat chat = (TdApi.Chat) object;
                    String avatarUrl = chat.photo.small.local.filePath;  // URL аватара
                    // Здесь можно обработать аватар
                }
            }
        });
    }

    // Получение информации о пользователе, включая аватар
    public static void getUserInfo(int userId) {
        TdApi.GetUser getUser = new TdApi.GetUser();
        getUser.userId = userId;
        
        // Асинхронный запрос для получения информации о пользователе
        client.send(getUser, new TdApi.Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {
                if (object.getConstructor() == TdApi.User.CONSTRUCTOR) {
                    TdApi.User user = (TdApi.User) object;
                    String avatarUrl = user.profilePhoto.small.local.filePath;  // URL аватара
                    // Здесь можно обработать аватар
                }
            }
        });
    }

    // Закрытие TDLib клиента
    public static void close() {
        client.close();
    }
}
