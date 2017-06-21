package kevslashnull.permissions.commands;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsGroup;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class SubGroup {
	
	public static void a(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("kp.groups")) {
			sender.sendMessage(PermissionsCommand.PREFIX + "§cSorry, but you are lacking a permission.");
			return;
		}
		if (args.length == 1) {
			SubGroups.a(sender, label, args);
			return;
		}
		if (args.length == 2) {
			PermissionsGroup g = KevsPermissions.getGroup(args[1]);
			if (g == null) {
				sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				sender.sendMessage(PermissionsCommand.PREFIX + "§7Use §8/§7" + label + " §d" + args[0] + " §7" + args[1]
						+ " §acreate §7to create it.");
				return;
			}
			sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + g.getMeta().getName() + " §7["
					+ g.getMeta().getID() + "]");
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Permissions§8: §r" + g.getPermissions().size());
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Prefix§8: §r" + g.getPrefix());
			sender.sendMessage(PermissionsCommand.SPACES_PREFIX + "§7Suffix§8: §r" + g.getSuffix());
		} else if (args.length == 3) {
			PermissionsGroup g = KevsPermissions.getGroup(args[1]);
			if (args[2].equalsIgnoreCase("create")) {
				if (g == null) {
					KevsPermissions.createGroup(args[1]);
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7created.");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7already exists.");
				}
			} else if (args[2].equalsIgnoreCase("delete")) {
				if (g != null) {
					KevsPermissions.deleteGroup(args[1]);
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7deleted.");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("prefix")) {
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Prefix of " + g.getMeta().getName() + "§8: §r"
							+ g.getPrefix());
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("suffix")) {
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Suffix of " + g.getMeta().getName() + "§8: §r"
							+ g.getSuffix());
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("options")) {
				if (g != null) {
					HashMap<String, Object> options = g.getMeta().getOptions();
					sender.sendMessage(PermissionsCommand.PREFIX + "Options of §e" + args[1]);
					for (String key : options.keySet()) {
						sender.sendMessage(key + "§8: §r" + options.get(key));
					}
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		} else if (args.length == 4) {
			if (args[2].equalsIgnoreCase("option")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					sender.sendMessage(PermissionsCommand.PREFIX + "Option \"" + args[3] + "\" of "
							+ g.getMeta().getName() + "§8: §r" + g.getMeta().getOptions().get(args[3]));
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("prefix")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					if (args[3].equalsIgnoreCase("-r"))
						g.getMeta().setOption("prefix", null);
					else
						g.getMeta().setOption("prefix", args[3].replace("\\_", " "));
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated prefix with new value (§r"
							+ args[3].replace("\\_", " ") + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("suffix")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					if (args[3].equalsIgnoreCase("-r"))
						g.getMeta().setOption("suffix", null);
					else
						g.getMeta().setOption("suffix", args[3].replace("\\_", " "));
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated suffix with new value (§r"
							+ args[3].replace("\\_", " ") + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("add")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					boolean set = g.addPermission(args[3]);
					if (!set)
						sender.sendMessage(
								PermissionsCommand.PREFIX + "That permission is already set for §e" + args[1] + "§7.");
					else
						sender.sendMessage(PermissionsCommand.PREFIX + "Added a new permission to §e" + args[1]
								+ " §7(§r" + args[3] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else if (args[2].equalsIgnoreCase("remove")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					boolean set = g.removePermission(args[3]);
					if (!set)
						sender.sendMessage(
								PermissionsCommand.PREFIX + "That permission is not set for §e" + args[1] + "§7.");
					else
						sender.sendMessage(PermissionsCommand.PREFIX + "Removed a permission from §e" + args[1]
								+ " §7(§r" + args[3] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		} else if (args.length == 5) {
			if (args[2].equalsIgnoreCase("option")) {
				PermissionsGroup g = KevsPermissions.getGroup(args[1]);
				if (g != null) {
					if (args[4].equalsIgnoreCase("-r"))
						g.getMeta().setOption(args[3], null);
					else
						g.getMeta().setOption(args[3], args[4]);
					sender.sendMessage(PermissionsCommand.PREFIX + "Updated option " + args[3] + " with new value (§r"
							+ args[4] + "§7).");
				} else {
					sender.sendMessage(PermissionsCommand.PREFIX + "Group §e" + args[1] + " §7does not exist.");
				}
			} else {
				sender.sendMessage(PermissionsCommand.PREFIX + "Use §8/§7kp §dhelp §7for help.");
			}
		}
	}
	
}
