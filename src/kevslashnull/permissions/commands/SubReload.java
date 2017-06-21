package kevslashnull.permissions.commands;

import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.PermissionsPlugin;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubReload {
	
	public static void a(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("kp.reload")) {
			sender.sendMessage(PermissionsCommand.PREFIX + "§cSorry, but you are lacking a permission.");
			return;
		}
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).reloadConfig();
		sender.sendMessage(PermissionsCommand.PREFIX + "Successfully reloaded config.");
		KevsPermissions.reload();
		sender.sendMessage(PermissionsCommand.PREFIX + "Successfully updated permissions data.");
	}
	
}
