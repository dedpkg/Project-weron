package com.example.telegrammod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.event.FMLClientSetupEvent;
import net.minecraftforge.fml.common.event.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TelegramMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TelegramMod
{
    // Это строка будет использоваться для идентификации мода в файлах конфигурации.
    public static final String MODID = "telegrammod";
    // Логгер для мода
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    // Пример настройки клиента
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        // Инициализация GUI
        LOGGER.info("Телеграмм мод для Minecraft загружен!");
    }

    // Это будет вызвано на старте клиента для авторизации
    public static void startLoginScreen() {
        Minecraft.getMinecraft().displayGuiScreen(new TelegramLoginGui());
    }
}
