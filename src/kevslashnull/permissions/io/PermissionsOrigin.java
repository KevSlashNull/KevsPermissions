package kevslashnull.permissions.io;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public enum PermissionsOrigin {
	SQL("sql"), FILE("file");
	
	private final String id;
	
	private PermissionsOrigin(String id) {
		this.id = id;
	}
	
	public String getAlphanumericalId() {
		return id;
	}
	
}
