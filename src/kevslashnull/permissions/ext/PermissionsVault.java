package kevslashnull.permissions.ext;

import org.bukkit.Bukkit;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.io.DataManager;
import net.milkbowl.vault.permission.Permission;

public class PermissionsVault extends Permission {
	
	@Override
	public String[] getGroups() {
		return KevsPermissions.getGroups().keySet().toArray(new String[] {});
	}
	
	@Override
	public String getName() {
		return "KevsPermissions";
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String[] getPlayerGroups(String arg0, String arg1) {
		return new String[] { KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(arg0)).getGroup().getMeta().getName() };
	}
	
	@Override
	public String getPrimaryGroup(String arg0, String arg1) {
		return getPlayerGroups(arg0, arg1)[0];
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean groupAdd(String arg0, String arg1, String arg2) {
		KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(arg0)).setGroup(arg2);
		return true;
	}
	
	@Override
	public boolean groupHas(String arg0, String arg1, String arg2) {
		return getPrimaryGroup(arg0, arg1).equalsIgnoreCase(arg2);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean groupRemove(String arg0, String arg1, String arg2) {
		KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(arg0)).setGroup(DataManager.getDefaultGroup());
		return true;
	}
	
	@Override
	public boolean hasGroupSupport() {
		return true;
	}
	
	@Override
	public boolean hasSuperPermsCompat() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return KevsPermissions.getPluginDetails() != null;
	}
	
	@Override
	public boolean playerAdd(String arg0, String arg1, String arg2) {
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean playerAddGroup(String arg0, String arg1, String arg2) {
		KevsPermissions.getPlayer(Bukkit.getOfflinePlayer(arg0)).setGroup(arg2);
		return true;
	}
	
	@Override
	public boolean playerHas(String arg0, String arg1, String arg2) {
		return false;
	}
	
	@Override
	public boolean playerInGroup(String arg0, String arg1, String arg2) {
		return getPrimaryGroup(arg0, arg1).equalsIgnoreCase(arg2);
	}
	
	@Override
	public boolean playerRemove(String arg0, String arg1, String arg2) {
		return false;
	}
	
	@Override
	public boolean playerRemoveGroup(String arg0, String arg1, String arg2) {
		return groupRemove(arg0, arg1, arg2);
	}
	
}
