package kevslashnull.permissions;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import kevslashnull.permissions.commands.PermissionsCommand;
import kevslashnull.permissions.ext.PermissionsVault;
import kevslashnull.permissions.io.ConfigDataManger;
import kevslashnull.permissions.io.DataManager;
import kevslashnull.permissions.listener.ConnectionListener;
import net.milkbowl.vault.permission.Permission;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class PermissionsPlugin extends JavaPlugin {
	
	/**
	 * @return the config
	 */
	protected static FileConfiguration config() {
		return getPlugin(PermissionsPlugin.class).getConfig();
	}
	
	/**
	 * Save the config! :3
	 */
	protected static void save() {
		getPlugin(PermissionsPlugin.class).saveConfig();
	}
	
	@Override
	public void onEnable() {
		// Let the watchdog sniff for plugins which can interfere with KP
		
		if (PermissionsWatchdog.sniffComplete()) {
			setEnabled(false);
			
			getLogger().log(Level.SEVERE, "");
			
			getServer().getConsoleSender().sendMessage(" [KevsPermissions] §cKevsPermissions error§r:");
			
			getLogger().log(Level.SEVERE, "");
			
			getLogger().log(Level.SEVERE,
					"Please note: KevsPermissions' watchdog has detected a plugin which could interfere with KevsPermissions and its functions. KevsPermissions is now going to shutdown itself.");
			
			getLogger().log(Level.SEVERE,
					"Error code provided by KevsPermissions watchdog: " + PermissionsWatchdog.errorCode());
			
			getLogger().log(Level.SEVERE, "");
			
			return;
		}
		
		// Creating config.yml if not exists
		
		saveDefaultConfig();
		
		// Index online players
		
		KevsPermissions.index(createDataManager());
		
		KevsPermissions.validateConfig();
		
		// Do some other cool stuff
		
		getCommand("kp").setExecutor(new PermissionsCommand());
		
		getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		
		// Register into vault
		
		getServer().getServicesManager().register(Permission.class, new PermissionsVault(), this,
				ServicePriority.Highest);
	}
	
	public static PluginDetails currentVersion() {
		return new PluginDetails(getPlugin(PermissionsPlugin.class).getDescription().getVersion(),
				getPlugin(PermissionsPlugin.class).getDescription().getAuthors());
	}
	
	static DataManager createDataManager() {
		return new ConfigDataManger(getInstance().getConfig());
	}
	
	static PermissionsPlugin getInstance() {
		return getPlugin(PermissionsPlugin.class);
	}
	
}
