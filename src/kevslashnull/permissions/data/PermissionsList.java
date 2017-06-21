package kevslashnull.permissions.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import kevslashnull.permissions.permissible.HasPermission;
import kevslashnull.permissions.permissible.KPermission;

/**
 * This list in read only. To edit permissions use the appropriate methods.
 * 
 * @author KevSlashNull
 * @since 2.0
 */
public class PermissionsList implements Iterable<KPermission> {
	
	private Collection<KPermission> list = new ArrayList<>();
	
	@Override
	public Iterator<KPermission> iterator() {
		return new ArrayList<KPermission>(list).iterator();
	}
	
	public PermissionsList(List<String> permissions) {
		for (String string : permissions) {
			KPermission p = KPermission.construct(string);
			if (p != null)
				list.add(p);
		}
	}
	
	@Override
	public String toString() {
		String str = "[";
		for (KPermission kPermission : list) {
			str += kPermission + ",";
		}
		return str.substring(0, str.length() - 1) + "]";
	}
	
	public HasPermission hasPermission(String inName) {
		for (KPermission kPermission : list) {
			if (kPermission.getName().equalsIgnoreCase(inName) || kPermission.getName().equalsIgnoreCase("*")) {
				return HasPermission.fromBoolean(kPermission.getType());
			}
		}
		return HasPermission.NOT_SET;
	}
	
	public int size() {
		return list.size();
	}
}
