package kevslashnull.permissions.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsPlayer;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubPlayers {
	
	public static void a(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("kp.players")) {
			sender.sendMessage(PermissionsCommand.PREFIX + "§cSorry, but you are lacking a permission.");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage(PermissionsCommand.PREFIX + "No command arguments required.");
			return;
		}
		sender.sendMessage(PermissionsCommand.PREFIX + "Online players (" + KevsPermissions.getPlayers().size() + "):");
		for (PermissionsPlayer g : KevsPermissions.getPlayers()) {
			sender.sendMessage("  " + Bukkit.getOfflinePlayer(UUID.fromString(g.getMeta().getName())).getName() + " ["
					+ g.getMeta().getID() + "]");
		}
	}
	
}
