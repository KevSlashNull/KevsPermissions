package kevslashnull.permissions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class PermissionsCommand implements CommandExecutor {
	
	public static final String PREFIX = "§6KevsPermissions §8┃ §7";
	public static final String SPACES_PREFIX = "                              ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(PREFIX + "You are running version " + KevsPermissions.getPluginDetails().getVersion()
					+ " of KevsPermissions, developed by §e" + KevsPermissions.getPluginDetails().getAuthor() + "§7.");
			sender.sendMessage("§7GitHub: https://github.com/KevSlashNull/KevsPermissions");
			return true;
		}
		if (!sender.hasPermission("kp.command")) {
			sender.sendMessage(PREFIX + "§cSorry, but you are lacking a permission.");
			return true;
		}
		if (args[0].equalsIgnoreCase("help")) {
			SubHelp.a(sender, label, args);
			return true;
		} else if (args[0].equalsIgnoreCase("groups")) {
			SubGroups.a(sender, label, args);
			return true;
		} else if (args[0].equalsIgnoreCase("group")) {
			SubGroup.a(sender, label, args);
			return true;
		} else if (args[0].equalsIgnoreCase("players")) {
			SubPlayers.a(sender, label, args);
			return true;
		} else if (args[0].equalsIgnoreCase("player")) {
			SubPlayer.a(sender, label, args);
			return true;
		} else if (args[0].equalsIgnoreCase("reload")) {
			SubReload.a(sender, label, args);
			return true;
		} else {
			error(sender);
		}
		return true;
	}
	
	private void error(CommandSender sender) {
		sender.sendMessage(PREFIX + "§7Unknown command. Use /kp help.");
	}
	
}
