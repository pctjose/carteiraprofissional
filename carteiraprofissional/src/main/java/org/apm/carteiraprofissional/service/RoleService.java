/**
 * 
 */
package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Role;

/**
 * @author Ezequiel Barreto
 *
 */
public interface RoleService {
	
	public Role getRole(Integer id);
	
	public void saveRole(Role role);

	public List<Role> getAllRole();


}
