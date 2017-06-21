package kevslashnull.permissions;

import org.bukkit.Bukkit;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class PermissionsWatchdog {
	
	public static boolean sniffForPEx() {
		return Bukkit.getPluginManager().isPluginEnabled("PermissionsEx");
	}
	
	public static boolean sniffForLuckyPerms() {
		return Bukkit.getPluginManager().isPluginEnabled("LuckPerms");
	}
	
	public static boolean sniffForZPermissions() {
		return Bukkit.getPluginManager().isPluginEnabled("zPermissions");
	}
	
	public static boolean sniffForBPermissions() {
		return Bukkit.getPluginManager().isPluginEnabled("bPermissions");
	}
	
	public static boolean sniffForGroupManager() {
		return Bukkit.getPluginManager().isPluginEnabled("GroupManager");
	}
	
	public static boolean sniffComplete() {
		return sniffForPEx() || sniffForBPermissions() || sniffForZPermissions() || sniffForLuckyPerms()
				|| sniffForGroupManager();
	}
	
	public static String errorCode() {
		if (sniffForPEx())
			return "watchdog-sniff-pex";
		if (sniffForBPermissions())
			return "watchdog-sniff-bperms";
		if (sniffForZPermissions())
			return "watchdog-sniff-zperms";
		if (sniffForLuckyPerms())
			return "watchdog-sniff-lperms";
		if (sniffForGroupManager())
			return "watchdog-sniff-gman";
		return "watchdog-ok";
	}
	
}
