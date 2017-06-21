package kevslashnull.permissions.io;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import kevslashnull.permissions.KevsPermissions;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public abstract class DataManager {
	
	private static DataManager manager;
	
	public static HashMap<String, Object> fetchPlayer(UUID id) {
		return manager.fetchPlayerById(id);
	}
	
	public static HashMap<String, Object> fetchGroup(String name) {
		return manager.fetchGroupByName(name);
	}
	
	public abstract HashMap<String, Object> fetchPlayerById(UUID id);
	
	public abstract HashMap<String, Object> fetchGroupByName(String name);
	
	public abstract PermissionsOrigin getTypeOfOrigin();
	
	public abstract String getDefault();
	
	public abstract HashMap<String, Object> getGroupList();
	
	public abstract HashMap<String, Object> createNewPlayer(UUID uniqueId);
	
	public abstract void createNewGroup(String id);
	
	public abstract void removeExistingGroup(String id);
	
	public abstract void changeOption(String name, String key, Object object, boolean player);
	
	public abstract void deletePlayerRecord(OfflinePlayer op);
	
	public abstract boolean setPermission(String uuid, String permission, boolean b);
	
	public abstract boolean unsetPermission(String uuid, String permission, boolean b);
	
	public abstract void setPlayerGroup(String name, String args);
	
	public static void setManager(DataManager newManager) {
		manager = newManager;
	}
	
	public static PermissionsOrigin getType() {
		return manager.getTypeOfOrigin();
	}
	
	public static String getDefaultGroup() {
		return manager.getDefault();
	}
	
	public static HashMap<String, Object> getGroups() {
		return manager.getGroupList();
	}
	
	public static HashMap<String, Object> createPlayer(UUID uniqueId) {
		return manager.createNewPlayer(uniqueId);
	}
	
	public static void createGroup(String string) {
		manager.createNewGroup(string);
		KevsPermissions.reload();
	}
	
	public static void removeGroup(String string) {
		manager.removeExistingGroup(string);
	}
	
	public static void setOption(String name, String key, Object object, boolean player) {
		manager.changeOption(name, key, object, player);
	}
	
	public static void deletePlayer(OfflinePlayer op) {
		manager.deletePlayerRecord(op);
	}
	
	public static boolean addPermission(String name, String permission, boolean b) {
		return manager.setPermission(name, permission, b);
	}
	
	public static boolean removePermission(String name, String string, boolean b) {
		return manager.unsetPermission(name, string, b);
	}
	
	public static void setGroup(String name, String args) {
		manager.setPlayerGroup(name, args);
	}
}
