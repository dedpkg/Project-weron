package com.example.telegrammod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TelegramMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TelegramMod {
    public static final String MODID = "telegrammod";
    private static final Minecraft mc = Minecraft.getInstance();

    // Этот метод будет вызван для инициализации мода
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // Например, можно подключить Telegram API для уведомлений при входе игрока
        System.out.println("Player " + event.getPlayer().getName().getString() + " has logged in.");
    }

    public static void init() {
        System.out.println("Initializing Telegram Mod");
        // Подключаемся к Telegram API, инициализируем бота и т.д.
    }
}
