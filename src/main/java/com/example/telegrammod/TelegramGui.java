package com.example.telegrammod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TelegramGui extends GuiScreen {
    private GuiButton sendButton;
    private GuiButton exitButton;
    private GuiTextField messageField;
    private List<String> messages;
    
    // Заглушка для данных чата (аватар и название)
    private String chatName = "Пример Чат";
    private String writingStatus = "Пишет: Никнейм";
    private int participantsCount = 5;

    @Override
    public void initGui() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        this.messages = new ArrayList<>();

        // Поле ввода сообщения
        this.messageField = new GuiTextField(this.fontRenderer, centerX - 100, this.height - 40, 200, 20);
        this.messageField.setFocused(true);

        // Кнопка отправки сообщения
        this.buttonList.add(this.sendButton = new GuiButton(0, centerX + 110, this.height - 40, 50, 20, "Send"));

        // Кнопка выхода
        this.buttonList.add(this.exitButton = new GuiButton(1, 10, 10, 40, 20, "Exit"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // Рисуем верхнюю шторку
        drawTopBar();

        // Рисуем сообщения
        int yOffset = this.height / 2 - 70;
        for (String msg : messages) {
            this.fontRenderer.drawString(msg, this.width / 2 - 100, yOffset, 0xFFFFFF);
            yOffset += 12;  // Смещение по вертикали
        }

        // Рисуем поле ввода сообщения
        this.messageField.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    // Рисуем верхнюю шторку с информацией о чате
    private void drawTopBar() {
        int topBarHeight = 50;
        this.drawRect(0, 0, this.width, topBarHeight, 0x80000000);  // Полупрозрачный фон для шторки

        // Аватар чата (заглушка)
        ResourceLocation avatar = new ResourceLocation("textures/avatars/chat_avatar.png");
        this.mc.getTextureManager().bindTexture(avatar);
        drawTexturedModalRect(10, 10, 0, 0, 40, 40);

        // Название чата
        this.fontRenderer.drawString(chatName, 60, 15, 0xFFFFFF);

        // Статус кто пишет
        this.fontRenderer.drawString(writingStatus, 60, 30, 0xAAAAAA);

        // Количество участников
        this.fontRenderer.drawString("Участников: " + participantsCount, this.width - 120, 30, 0xAAAAAA);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            String message = this.messageField.getText();
            if (!message.isEmpty()) {
                messages.add("Вы: " + message);  // Добавление сообщения в список
                TelegramHandler.sendMessageToTelegram(message);  // Отправка сообщения через Telegram
                this.messageField.setText("");  // Очистка поля ввода
            }
        } else if (button.id == 1) {
            this.mc.displayGuiScreen(null);  // Закрытие GUI
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        this.messageField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
