package testing;

import com.gmail.favorlock.bungeechatplus.BungeeChatPlus;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.PluginDescription;

import static org.mockito.Mockito.*;

public abstract class BaseUnit {
	
	public PluginDescription pdf;
	public BungeeChatPlus plugin;
	public ProxiedPlayer player;
	
	public BaseUnit() {
		pdf = mock(PluginDescription.class);
		plugin = mock(BungeeChatPlus.class);
		player = mock(ProxiedPlayer.class);
	}

}
