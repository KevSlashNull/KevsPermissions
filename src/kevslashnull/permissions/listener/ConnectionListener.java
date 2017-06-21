package kevslashnull.permissions.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import kevslashnull.permissions.KevsPermissions;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class ConnectionListener implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		KevsPermissions.registerPlayer(e.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		KevsPermissions.unregisterPlayer(e.getPlayer());
	}
	
}
