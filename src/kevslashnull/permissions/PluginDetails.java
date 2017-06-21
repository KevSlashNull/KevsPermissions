package kevslashnull.permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KevSlashNull
 * @since 2.0
 */
public final class PluginDetails {
	
	private final String version;
	private final List<String> author;
	
	PluginDetails(String version, List<String> list) {
		this.version = version;
		this.author = list;
	}
	
	public String getAuthor() {
		return author.get(0);
	}
	
	public List<String> getAuthors() {
		return new ArrayList<>(author);
	}
	
	public String getVersion() {
		return version;
	}
	
}
