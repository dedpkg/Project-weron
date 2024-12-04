package com.example.telegrammod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class TelegramLoginGui extends GuiScreen {
    private GuiButton submitButton;
    private GuiTextField phoneNumberField;
    private GuiTextField codeField;
    private boolean isPhoneEntered = false;

    @Override
    public void initGui() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Поле ввода номера телефона
        phoneNumberField = new GuiTextField(this.fontRenderer, centerX - 100, centerY - 20, 200, 20);

        // Поле ввода кода (будет отображаться только после ввода телефона)
        codeField = new GuiTextField(this.fontRenderer, centerX - 100, centerY + 20, 200, 20);
        codeField.setEnabled(false);  // Пока поле для кода не активно

        // Кнопка отправки данных
        this.buttonList.add(submitButton = new GuiButton(0, centerX - 50, centerY + 60, 100, 20, "Submit"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            if (!isPhoneEntered) {
                String phoneNumber = phoneNumberField.getText();
                if (!phoneNumber.isEmpty()) {
                    // Отправка запроса на получение кода через TDLib
                    TelegramHandler.requestCode(phoneNumber);  // Механизм для запроса кода
                    isPhoneEntered = true;  // Переключаем на ввод кода
                    phoneNumberField.setEnabled(false);  // Блокируем поле для телефона
                    codeField.setEnabled(true);  // Разрешаем ввод кода
                    submitButton.displayString = "Verify Code";
                }
            } else {
                String code = codeField.getText();
                if (!code.isEmpty()) {
                    // Отправка кода для авторизации
                    TelegramHandler.verifyCode(code);  // Механизм для проверки кода
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // Отображаем текстовые поля
        phoneNumberField.drawTextBox();
        codeField.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        phoneNumberField.textboxKeyTyped(typedChar, keyCode);
        codeField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
