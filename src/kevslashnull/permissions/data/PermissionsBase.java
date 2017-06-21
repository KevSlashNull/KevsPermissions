package kevslashnull.permissions.data;

import kevslashnull.permissions.io.PermissionsMeta;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public interface PermissionsBase {
	
	public PermissionsMeta getMeta();
	
	public PermissionsList getPermissions();
	
	public String getPrefix();
	
	public String getSuffix();
	
	public boolean addPermission(String string);
	
	public boolean removePermission(String string);
}
