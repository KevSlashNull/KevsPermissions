package kevslashnull.permissions.io;

import java.util.HashMap;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public interface PermissionsMeta {
	
	public int getID();
	
	public String getName();
	
	public long getFetchDate();
	
	public PermissionsOrigin getOrigin();
	
	public HashMap<String, Object> getOptions();
	
	public void setOption(String string, Object object);
}
