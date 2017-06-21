package kevslashnull.permissions.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsPlayer;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubPlayer {
	
	@SuppressWarnings("deprecation")
	public static void a(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("kp.players")) {
			sender.sendMessage(PermissionsCommand.PREFIX + "§cSorry, but you are lacking a permission.");
			return;
		}
		if (args.length == 1) {
			SubPlayers.a(sender, label, args);
			return;
		}
		if (args.length == 2) {
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
			PermissionsPlayer p = KevsPermissions.getPlayer(op);
			if (p == null) {
				boolean existed = KevsPermissions.registerPlayer(op);
				if (existed)
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1]
							+ " §7is not online, but their data was loaded from the local entrys.");
				else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1]
							+ " §7did not exist in local files. An entry was created.");
					sender.sendMessage(PermissionsCommand.PREFIX + "§7Use §8/§7kp §dplayer §7" + args[1]
							+ " §adelete §7to delete their entry.");
				}
				return;
			}
			sender.sendMessage(PermissionsCommand.PREFIX + "Player §e"
					+ Bukkit.getOfflinePlayer(UUID.fromString(p.getMeta().getName())).getName() + " §7["
					+ p.getMeta().getID() + "]");
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Group§8: §r" + p.getGroup().getMeta().getName());
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Permissions§8: §r" + p.getPermissions().size());
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Prefix§8: §r" + p.getPrefix());
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Suffix§8: §r" + p.getSuffix());
		} else if (args.length == 3) {
			PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
			if (args[2].equalsIgnoreCase("prefix")) {
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Prefix of " + g.getMeta().getName() + "§8: §r"
							+ g.getPrefix());
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("suffix")) {
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Suffix of " + g.getMeta().getName() + "§8: §r"
							+ g.getSuffix());
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("options")) {
				if (g != null) {
					HashMap<String, Object> options = g.getMeta().getOptions();
					sender.sendMessage(PermissionsCommand.PREFIX + "Options of §e" + args[1] + "§8:");
					for (String key : options.keySet()) {
						sender.sendMessage(PermissionsCommand.SPACES_PREFIX + key + "§8: §r" + options.get(key));
					}
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("group")) {
				if (g != null) {
					Bukkit.dispatchCommand(sender, "kp group " + g.getGroup().getMeta().getName());
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("delete")) {
				if (g != null) {
					if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
						sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1]
								+ " §7is online, kick them first please.");
						return;
					}
					KevsPermissions.deletePlayer(Bukkit.getOfflinePlayer(args[1]));
					sender.sendMessage(PermissionsCommand.PREFIX + "Entry of §e" + args[1] + " §7was deleted.");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		} else if (args.length == 4) {
			if (args[2].equalsIgnoreCase("option")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Option \"" + args[3] + "\" of "
							+ g.getMeta().getName() + "§8: §r" + g.getMeta().getOptions().get(args[3]));
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("prefix")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					if (args[3].equalsIgnoreCase("-r"))
						g.getMeta().setOption("prefix", null);
					else
						g.getMeta().setOption("prefix", args[3].replace("\\_", " "));
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated prefix with new value (§r"
							+ args[3].replace("\\_", " ") + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("suffix")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					if (args[3].equalsIgnoreCase("-r"))
						g.getMeta().setOption("suffix", null);
					else
						g.getMeta().setOption("suffix", args[3].replace("\\_", " "));
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated suffix with new value (§r"
							+ args[3].replace("\\_", " ") + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("add")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					boolean set = g.addPermission(args[3]);
					if (!set)
						sender.sendMessage(
								PermissionsCommand.PREFIX + "That permission is already set for §e" + args[1] + "§7.");
					else
						sender.sendMessage(PermissionsCommand.PREFIX + "Added a new permission to §e" + args[1]
								+ " §7(§r" + args[3] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("group")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					g.setGroup(args[3]);
					sender.sendMessage(
							PermissionsCommand.PREFIX + "Changed group of §e" + args[1] + " §7(§r" + args[3] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("remove")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					boolean set = g.removePermission(args[3]);
					if (!set)
						sender.sendMessage(
								PermissionsCommand.PREFIX + "That permission is not set for §e" + args[1] + "§7.");
					else
						sender.sendMessage(PermissionsCommand.PREFIX + "Removed a permission from §e" + args[1]
								+ " §7(§r" + args[3] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		} else if (args.length == 5) {
			if (args[2].equalsIgnoreCase("option")) {
				PermissionsPlayer g = KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(args[1]));
				if (g != null) {
					if (args[4].equalsIgnoreCase("-r"))
						g.getMeta().setOption(args[3], null);
					else
						g.getMeta().setOption(args[3], args[4]);
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated option " + args[3] + " with new value (§r"
							+ args[4] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Player §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		}
	}
	
}
