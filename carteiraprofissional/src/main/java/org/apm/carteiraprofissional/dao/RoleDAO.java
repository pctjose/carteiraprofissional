/**
 * 
 */
package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Role;

/**
 * @author Ezequiel Barreto
 *
 */
public interface RoleDAO {
	
	public void saveRole(Role role);
	
	public Role getRole(Integer id);

	public List<Role> getAllRole();

	public Role getByID(Integer id);

	public Role getByUUID(String uuid);

	public Role getByDesignacao(String designacao);

}
