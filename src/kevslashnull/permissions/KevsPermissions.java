package kevslashnull.permissions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

import kevslashnull.permissions.data.PermissionsGroup;
import kevslashnull.permissions.data.PermissionsList;
import kevslashnull.permissions.data.PermissionsPlayer;
import kevslashnull.permissions.io.DataManager;
import kevslashnull.permissions.io.PermissionsFactory;
import kevslashnull.permissions.io.PermissionsMeta;
import kevslashnull.permissions.io.PermissionsOrigin;
import kevslashnull.permissions.permissible.KevsPermissible;

/**
 * The official KevsPermissions API for Spigot.
 * 
 * @author KevSlashNull
 * @since 2.0
 */
public final class KevsPermissions {
	
	private KevsPermissions() {
	}
	
	private static ArrayList<PermissionsPlayer> players = new ArrayList<>();
	
	private static HashMap<String, PermissionsGroup> groups = new HashMap<>();
	
	public static PermissionsPlayer getPlayer(UUID id) {
		if (id == null)
			throw new IllegalArgumentException("UUID is not supposed to be null.");
		return getPlayer(Bukkit.getOfflinePlayer(id));
	}
	
	public static PermissionsPlayer getPlayer(OfflinePlayer op) {
		if (op == null)
			throw new IllegalArgumentException("OfflinePlayer is not supposed to be null.");
		for (PermissionsPlayer permissionsPlayer : players) {
			if (permissionsPlayer.getUniqueId().equals(op.getUniqueId()))
				return permissionsPlayer;
		}
		return null;
	}
	
	public static PermissionsGroup getGroup(String name) {
		if (name == null)
			throw new IllegalArgumentException("String is not supposed to be null.");
		for (PermissionsGroup g : groups.values()) {
			if (g.getMeta().getName().equalsIgnoreCase(name)) {
				return g;
			}
		}
		return null;
	}
	
	static void index(DataManager manager) {
		DataManager.setManager(manager);
		HashMap<String, Object> groups = DataManager.getGroups();
		if (groups != null)
			for (String key : groups.keySet()) {
				if (!(groups.get(key) instanceof MemorySection))
					continue;
				
				PermissionsGroup pg = registerGroup((MemorySection) groups.get(key), key);
				if (pg != null)
					KevsPermissions.groups.put(key, pg);
			}
		
		if (KevsPermissions.groups.size() == 0 || KevsPermissions.groups.get(DataManager.getDefaultGroup()) == null) {
			HashMap<String, Object> options = new HashMap<>();
			options.put("prefix", "");
			options.put("suffix", "");
			KevsPermissions.groups
					.put(DataManager.getDefaultGroup(),
							PermissionsFactory.createGroup(
									PermissionsFactory.createMeta(0, 0, DataManager.getDefaultGroup(),
											PermissionsOrigin.FILE, options, false),
									new PermissionsList(new ArrayList<>())));
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			registerPlayer(p);
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Only use this if you know what you are doing!
	 * 
	 * @param p
	 *            the player
	 */
	public static boolean registerPlayer(OfflinePlayer op) {
		HashMap<String, Object> player = DataManager.fetchPlayer(op.getUniqueId());
		boolean e = true;
		if (player == null) {
			player = DataManager.createPlayer(op.getUniqueId());
			e = false;
		}
		Integer id = (Integer) player.get("id");
		String group = (String) player.get("group");
		List<String> permissions = (List<String>) player.get("permissions");
		Object options = player.get("options");
		Map<String, Object> newOptions;
		if (options == null)
			newOptions = new HashMap<>();
		else {
			if (options instanceof MemorySection) {
				MemorySection s = (MemorySection) options;
				newOptions = s.getValues(true);
			} else {
				newOptions = (Map<String, Object>) options;
			}
		}
		
		if (permissions == null)
			permissions = new ArrayList<>();
		if (id == null)
			id = 0;
		if (group == null)
			group = DataManager.getDefaultGroup();
		
		PermissionsMeta meta = PermissionsFactory.createMeta(id, System.currentTimeMillis(),
				op.getUniqueId().toString(), DataManager.getType(), newOptions, true);
		
		PermissionsPlayer pp = PermissionsFactory.createPlayer(meta, new PermissionsList(permissions), op.getUniqueId(),
				group);
		
		players.add(pp);
		
		if (op.isOnline())
			injectToPlayer(op.getPlayer(), pp);
		return e;
	}
	
	@SuppressWarnings("unchecked")
	static PermissionsGroup registerGroup(MemorySection section, String key) {
		int id = section.getInt("id", 0);
		List<String> permissions = (List<String>) section.get("permissions", new ArrayList<>());
		
		// HashMap<String, Object> options = new HashMap<>();
		// options.put("prefix", section.getString("prefix"));
		// options.put("suffix", section.getString("suffix"));
		MemorySection sec;
		HashMap<String, Object> options;
		try {
			sec = (MemorySection) section.get("options");
			options = (HashMap<String, Object>) sec.getValues(false);
		} catch (Exception e) {
			options = new HashMap<>();
		}
		
		PermissionsMeta meta = PermissionsFactory.createMeta(id, System.currentTimeMillis(), key, DataManager.getType(),
				options, false);
		
		PermissionsGroup pg = PermissionsFactory.createGroup(meta, new PermissionsList(permissions));
		;
		return pg;
	}
	
	static void injectToPlayer(Player p, PermissionsPlayer pp) {
		try {
			Method getHandle = p.getClass().getMethod("getHandle");
			Object human = getHandle.invoke(p);
			String pck = new String(human.getClass().getCanonicalName()).split("\\.")[3];
			Field field = Class.forName("org.bukkit.craftbukkit." + pck + ".entity.CraftHumanEntity")
					.getDeclaredField("perm");
			field.setAccessible(true);
			Field modifiersField = field.getClass().getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(p, new KevsPermissible(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void validateConfig() { // TODO
	}
	
	public static PermissionsGroup getDefaultGroup() {
		return getGroup(DataManager.getDefaultGroup());
	}
	
	public static PluginDetails getPluginDetails() {
		return PermissionsPlugin.currentVersion();
	}
	
	/**
	 * 
	 * @return all registered groups
	 */
	public static HashMap<String, PermissionsGroup> getGroups() {
		return new HashMap<>(groups);
	}
	
	public static void createGroup(String string) {
		if (!StringUtils.isAlphanumeric(string)) {
			return;
		}
		DataManager.createGroup(string);
		reload();
	}
	
	public static void reload() {
		PermissionsPlugin.getInstance().reloadConfig();
		players.clear();
		groups.clear();
		index(PermissionsPlugin.createDataManager());
	}
	
	public static void deleteGroup(String string) {
		groups.remove(string);
		DataManager.removeGroup(string);
	}
	
	public static ArrayList<PermissionsPlayer> getPlayers() {
		return new ArrayList<>(players);
	}
	
	public static void unregisterPlayer(Player player) {
		players.remove(getPlayer(player));
	}
	
	public static void deletePlayer(OfflinePlayer op) {
		DataManager.deletePlayer(op);
	}
	
}
