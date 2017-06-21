package kevslashnull.permissions.data;

import java.util.UUID;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public interface PermissionsPlayer extends PermissionsBase {
	
	public PermissionsGroup getGroup();
	
	public UUID getUniqueId();
	
	@Deprecated
	public boolean hasPermission();
	
	public void setGroup(String args);
	
}
