package com.gmail.favorlock.bungeechatplus.listeners;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import com.gmail.favorlock.bungeechatplus.BungeeChatPlus;
import com.gmail.favorlock.bungeechatplus.entities.Channel;
import com.gmail.favorlock.bungeechatplus.entities.Chatter;
import com.google.common.eventbus.Subscribe;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;

public class PluginMessageListener implements Listener {
	
	BungeeChatPlus plugin;
	
	public PluginMessageListener(BungeeChatPlus plugin) {
		this.plugin = plugin;
	}
	
	@Subscribe
	public void receivePluginMessage(PluginMessageEvent event) throws IOException {
		if (!event.getTag().equalsIgnoreCase("BungeeChatPlus")) {
			return;
		}
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
		String channel = in.readUTF();
		if (channel.equalsIgnoreCase("VaultAffix")) {
			String player = in.readUTF();
			Chatter chatter = plugin.getChatterManager().getChatter(player);
			chatter.setPrefix(in.readUTF());
			chatter.setSuffix(in.readUTF());
		}
		else if (channel.equalsIgnoreCase("HeroChat")) {
			String name = in.readUTF();
			String message = in.readUTF();

			ProxiedPlayer p = null;
			for (ProxiedPlayer player : plugin.getPlayers()) {
				if (player.getName().equals(name)) {
					p = player;
				}
			}
			if (p != null) {
				if (plugin.getChatterManager().getChatter(p.getName()) == null) {
					plugin.getChatterManager().loadChatter(p.getName());
				}

				Chatter chatter = plugin.getChatterManager().getChatter(p.getName());
				Channel chatChannel = chatter.getActiveChannel();
				chatChannel.sendMessage(p, message);
			}

		}
	}

}
