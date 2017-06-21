package kevslashnull.permissions.commands;

import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsGroup;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubGroups {
	
	public static void a(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("kp.groups")) {
			sender.sendMessage(PermissionsCommand.PREFIX + "§cSorry, but you are lacking a permission.");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage(PermissionsCommand.PREFIX + "No command arguments required.");
			return;
		}
		sender.sendMessage(
				PermissionsCommand.PREFIX + "Registered groups (" + KevsPermissions.getGroups().size() + "):");
		for (PermissionsGroup g : KevsPermissions.getGroups().values()) {
			sender.sendMessage("  " + g.getMeta().getName() + " [" + g.getMeta().getID() + "]");
		}
	}
	
}
