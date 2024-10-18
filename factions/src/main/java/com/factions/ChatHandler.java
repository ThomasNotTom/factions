package com.factions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10 * 1000, 100, true, true));
        player.getServer().broadcastMessage("Welcome, " + ChatColor.BLUE + event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();

        Faction faction = FactionHandler.getPlayerFaction(event.getPlayer().getUniqueId());
        String prefix = "";
        if (faction != null) {
            prefix = "[" + faction.chatColor + faction.name + ChatColor.WHITE + "]";
        }
        TextComponent prefixComponent = new TextComponent(prefix);

        if (faction != null) {
            prefixComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(
                            "-- " + faction.chatColor + faction.name + ChatColor.WHITE + " --\n" + "Value - £"
                                    + ChatColor.GREEN
                                    + faction.money)
                            .create()));
        }

        TextComponent playerMessage = new TextComponent(playerName + ": " + message);

        // Cancel the default chat message
        event.setCancelled(true);

        // Send the new formatted message to all players (broadcast both components)
        for (

        Player player : event.getRecipients()) {
            player.spigot().sendMessage(prefixComponent, playerMessage);
        }
    }
}
