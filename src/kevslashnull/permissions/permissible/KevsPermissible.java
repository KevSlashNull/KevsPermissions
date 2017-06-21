package kevslashnull.permissions.permissible;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.ServerOperator;

import kevslashnull.permissions.KevsPermissions;
import kevslashnull.permissions.data.PermissionsPlayer;

/**
 * 
 * @author KevSlashNull
 * @since 2.0
 */
public class KevsPermissible extends PermissibleBase {
	
	private Player p;
	
	public KevsPermissible(ServerOperator opable) {
		super(opable);
		p = (Player) opable;
	}
	
	@Override
	public boolean hasPermission(Permission perm) {
		return hasPermission(perm.getName());
	}
	
	@Override
	public boolean hasPermission(String inName) {
		PermissionsPlayer player = KevsPermissions.getPlayer(p.getUniqueId());
		if (!isPermissionSet(inName)) {
			return player.getGroup().getPermissions().hasPermission(inName).toBoolean();
		}
		return player.getPermissions().hasPermission(inName).toBoolean()
				|| player.getPermissions().hasPermission("*").toBoolean();
	}
	
	@Override
	public boolean isPermissionSet(Permission perm) {
		return isPermissionSet(perm.getName());
	}
	
	@Override
	public boolean isPermissionSet(String name) {
		PermissionsPlayer player = KevsPermissions.getPlayer(p.getUniqueId());
		return player.getPermissions().hasPermission(name) != HasPermission.NOT_SET;
	}
	
}
