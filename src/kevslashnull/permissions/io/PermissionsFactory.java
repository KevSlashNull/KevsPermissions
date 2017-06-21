package kevslashnull.permissions.io;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsGroup;
import kevslashnull.permissions.data.PermissionsList;
import kevslashnull.permissions.data.PermissionsPlayer;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public final class PermissionsFactory {
	
	private PermissionsFactory() {
	}
	
	/**
	 * 
	 * @param id
	 *            The numerical ID.
	 * @param fetchDate
	 *            The date when the meta-information was fetched.
	 * @param name
	 *            The name of the object.
	 * @param origin
	 *            The origin of the information.
	 * @return A brand new created {@link PermissionsMeta}
	 * @throws IllegalArgumentException
	 *             if {@link PermissionsOrigin} is null.
	 */
	public static PermissionsMeta createMeta(int id, long fetchDate, String name, PermissionsOrigin origin,
			Map<String, Object> options, boolean player) {
		if (origin == null)
			throw new IllegalArgumentException("PermissionsOrigin is not supposed to be null.");
		return new PermissionsMeta() {
			
			@Override
			public PermissionsOrigin getOrigin() {
				return origin;
			}
			
			@Override
			public String getName() {
				return name;
			}
			
			@Override
			public int getID() {
				return id;
			}
			
			@Override
			public long getFetchDate() {
				return fetchDate;
			}
			
			@Override
			public HashMap<String, Object> getOptions() {
				return (HashMap<String, Object>) options;
			}
			
			@Override
			public void setOption(String string, Object object) {
				DataManager.setOption(name, string, object, player);
			}
		};
	}
	
	public static PermissionsPlayer createPlayer(PermissionsMeta meta, PermissionsList list, UUID uuid, String group) {
		return new PermissionsPlayer() {
			
			@Override
			public String getSuffix() {
				return (String) getMeta().getOptions().get("suffix");
			}
			
			@Override
			public String getPrefix() {
				return (String) getMeta().getOptions().get("prefix");
			}
			
			@Override
			public PermissionsList getPermissions() {
				return list;
			}
			
			@Override
			public PermissionsMeta getMeta() {
				return meta;
			}
			
			@Override
			public PermissionsGroup getGroup() {
				PermissionsGroup g = KevsPermissions.getGroup(group);
				if (g == null) {
					return KevsPermissions.getDefaultGroup();
				}
				return g;
			}
			
			@Override
			public UUID getUniqueId() {
				return uuid;
			}
			
			@Override
			@Deprecated // TODO
			public boolean hasPermission() {
				return false;
			}
			
			@Override
			public boolean addPermission(String permission) {
				return DataManager.addPermission(meta.getName(), permission, true);
			}
			
			@Override
			public boolean removePermission(String string) {
				return DataManager.removePermission(meta.getName(), string, false);
			}
			
			@Override
			public void setGroup(String args) {
				DataManager.setGroup(meta.getName(), args);
			}
		};
	}
	
	public static PermissionsGroup createGroup(PermissionsMeta meta, PermissionsList list) {
		return new PermissionsGroup() {
			
			@Override
			public String getSuffix() {
				return (String) getMeta().getOptions().get("suffix");
			}
			
			@Override
			public String getPrefix() {
				return (String) getMeta().getOptions().get("prefix");
			}
			
			@Override
			public PermissionsList getPermissions() {
				return list;
			}
			
			@Override
			public PermissionsMeta getMeta() {
				return meta;
			}
			
			@Override
			public boolean addPermission(String string) {
				return DataManager.addPermission(meta.getName(), string, false);
			}
			
			@Override
			public boolean removePermission(String string) {
				return DataManager.removePermission(meta.getName(), string, false);
			}
		};
	}
	
}
