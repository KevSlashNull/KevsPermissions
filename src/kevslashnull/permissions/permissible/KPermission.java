package kevslashnull.permissions.permissible;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public interface KPermission {
	
	public static KPermission construct(String string) {
		if (string == null || string.length() == 0)
			return null;
		boolean type = string.startsWith("-");
		String name = type ? new String(string).substring(1, string.length()) : new String(string);
		return new KPermission() {
			
			@Override
			public String getName() {
				return name;
			}
			
			@Override
			public boolean getType() {
				return !type;
			}
			
			@Override
			public String toString() {
				return string;
			}
		};
	}
	
	public String getName();
	
	public boolean getType();
}
