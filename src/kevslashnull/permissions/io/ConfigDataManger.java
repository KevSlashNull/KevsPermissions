package kevslashnull.permissions.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.PermissionsPlugin;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public class ConfigDataManger extends DataManager {
	
	private FileConfiguration config;
	
	public ConfigDataManger(FileConfiguration config) {
		this.config = config;
	}
	
	@Override
	public HashMap<String, Object> fetchPlayerById(UUID id) {
		ConfigurationSection section = config.getConfigurationSection("players." + id);
		if (section == null)
			return null;
		return (HashMap<String, Object>) section.getValues(true);
	}
	
	@Override
	public HashMap<String, Object> fetchGroupByName(String name) {
		ConfigurationSection section = config.getConfigurationSection("groups." + name);
		if (section == null)
			return null;
		return (HashMap<String, Object>) section.getValues(true);
	}
	
	@Override
	public PermissionsOrigin getTypeOfOrigin() {
		return PermissionsOrigin.FILE;
	}
	
	@Override
	public String getDefault() {
		return config.getString("default-group", "default");
	}
	
	@Override
	public HashMap<String, Object> getGroupList() {
		ConfigurationSection section = config.getConfigurationSection("groups");
		if (section == null)
			return null;
		return (HashMap<String, Object>) section.getValues(false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> createNewPlayer(UUID uniqueId) {
		HashMap<String, Object> player = new HashMap<>();
		player.put("id", ThreadLocalRandom.current().nextInt(0, 100));
		player.put("permissions", Arrays.asList("kp.empty"));
		player.put("group", getDefault());
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("kp_cv", KevsPermissions.getPluginDetails().getVersion());
		player.put("options", options);
		config.set("players." + uniqueId, player);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		return (HashMap<String, Object>) config.get("players." + uniqueId); // MemorySection...
	}
	
	@Override
	public void createNewGroup(String id) {
		HashMap<String, Object> group = new HashMap<>();
		group.put("id", ThreadLocalRandom.current().nextInt(0, 100));
		group.put("permissions", Arrays.asList("kp.empty"));
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("kp_cv", KevsPermissions.getPluginDetails().getVersion());
		options.put("prefix", "");
		options.put("suffix", "");
		group.put("options", options);
		config.set("groups." + id, group);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
	}
	
	@Override
	public void removeExistingGroup(String id) {
		config.set("groups." + id, null);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
	}
	
	@Override
	public void changeOption(String name, String key, Object object, boolean player) {
		if (player)
			config.set("players." + name + ".options." + key, object);
		else
			config.set("groups." + name + ".options." + key, object);
		
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		KevsPermissions.reload();
	}
	
	@Override
	public void deletePlayerRecord(OfflinePlayer op) {
		if (op.isOnline())
			return;
		config.set("players." + op.getUniqueId(), null);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		KevsPermissions.reload();
	}
	
	@Override
	public boolean setPermission(String uuid, String permission, boolean b) {
		List<String> list = config.getStringList((b ? "players." : "groups.") + uuid + ".permissions");
		if (list.contains(permission))
			return false;
		list.add(permission);
		config.set((b ? "players." : "groups.") + uuid + ".permissions", list);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		KevsPermissions.reload();
		return true;
	}
	
	@Override
	public boolean unsetPermission(String uuid, String permission, boolean b) {
		List<String> list = config.getStringList((b ? "players." : "groups.") + uuid + ".permissions");
		if (!list.contains(permission))
			return false;
		list.remove(permission);
		config.set((b ? "players." : "groups.") + uuid + ".permissions", list);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		KevsPermissions.reload();
		return true;
	}
	
	@Override
	public void setPlayerGroup(String name, String args) {
		config.set("players." + name + ".group", args);
		PermissionsPlugin.getPlugin(PermissionsPlugin.class).saveConfig();
		KevsPermissions.reload();
	}
}
