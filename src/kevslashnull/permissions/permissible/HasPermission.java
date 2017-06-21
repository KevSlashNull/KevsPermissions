package kevslashnull.permissions.permissible;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public enum HasPermission {
	TRUE(true), FALSE(false), NOT_SET(false);
	
	private boolean bool;
	
	private HasPermission(boolean b) {
		this.bool = b;
	}
	
	public boolean toBoolean() {
		return bool;
	}
	
	public static HasPermission fromBoolean(boolean type) {
		if (type)
			return TRUE;
		return FALSE;
	}
}
