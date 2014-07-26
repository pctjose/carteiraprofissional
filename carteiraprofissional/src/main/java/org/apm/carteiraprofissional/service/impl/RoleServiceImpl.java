/**
 * 
 */
package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Role;
import org.apm.carteiraprofissional.dao.RoleDAO;
import org.apm.carteiraprofissional.service.RoleService;

/**
 * @author Ezequiel Barreto
 *
 */
public class RoleServiceImpl implements RoleService{
	
	private RoleDAO roleDAO;
	private static Logger log = Logger.getLogger(RoleServiceImpl.class);
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	
	public Role getRole(Integer id) {
		return roleDAO.getRole(id);
	}

	
	public void saveRole(Role role) {
		log.info(roleDAO);
		roleDAO.saveRole(role);
	}

	public List<Role> getAllRole() {
		log.info(roleDAO);
		return (List<Role>) roleDAO.getAllRole();
	}

}
