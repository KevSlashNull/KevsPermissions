package kevslashnull.permissions.commands;

import org.bukkit.command.CommandSender;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubHelp {
	
	public static void a(CommandSender sender, String label, String[] args) {
		sender.sendMessage(PermissionsCommand.PREFIX + "Registered commands:");
		sender.sendMessage("  §8/§7kp §dhelp §8- §7Show this page");
		sender.sendMessage("  §8/§7kp §dgroups §8- §7List all groups");
		sender.sendMessage("  §8/§7kp §dplayers §8- §7List all online players");
		sender.sendMessage("  §8/§7kp §dgroup [group] §8- §7Group overview");
		sender.sendMessage("  §8/§7kp §dgroup [group] create §8- §7Create a group");
		sender.sendMessage("  §8/§7kp §dgroup [group] delete §8- §7Delete a group");
		sender.sendMessage("  §8/§7kp §dgroup [group] prefix|suffix §8- §7Fancy stuff");
		sender.sendMessage("  §8/§7kp §dgroup [group] add|remove [permission] §8- §7Set permissions");
		sender.sendMessage("  §8/§7kp §dgroup [group] options §8- §7List all options");
		sender.sendMessage("  §8/§7kp §dgroup [group] option §8- §7See an option");
		sender.sendMessage("  §8/§7kp §dgroup [group] option [value] §8- §7Set an option");
		sender.sendMessage("  §8/§7kp §dplayer [player] §8- §7Player overview");
		sender.sendMessage("    §cWill create the player if it does not exist.");
		sender.sendMessage("  §8/§7kp §dplayer [player] delete §8- §7Delete a  player");
		sender.sendMessage("  §8/§7kp §dplayer [player] prefix|suffix §8- §7Fancy stuff");
		sender.sendMessage("  §8/§7kp §dplayer [player] add|remove [permission] §8- §7Set permissions");
		sender.sendMessage("  §8/§7kp §dplayer [player] options §8- §7List all options");
		sender.sendMessage("  §8/§7kp §dplayer [player] option §8- §7See an option");
		sender.sendMessage("  §8/§7kp §dplayer [player] option [value] §8- §7Set an option");
		sender.sendMessage(
				"§7§lCommand Cheatsheet: §ahttps://github.com/KevSlashNull/KevsPermissions/wiki/1.1-Commands");
	}
}
