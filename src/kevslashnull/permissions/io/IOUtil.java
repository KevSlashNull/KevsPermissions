package kevslashnull.permissions.io;

/**
 * Just simple IO things. And some string processing.
 * 
 * @author KevSlashNull
 * @since 2.0
 */
public final class IOUtil {
	
	public static boolean empty(String str) {
		if (str == null || str.length() == 0)
			return true;
		for (char c : str.toCharArray())
			if (c != ' ')
				return false;
		return true;
	}
	
	public static boolean massVerifyNotNull(Object... objects) throws NullPointerException {
		for (Object object : objects) {
			if (object == null)
				throw new NullPointerException("An unknown value was null.");
		}
		return true;
	}
	
}
