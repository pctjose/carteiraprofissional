package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.TipoDocumento;
import org.apm.carteiraprofissional.dao.TipoDocumentoDAO;
import org.apm.carteiraprofissional.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	@Autowired
	private TipoDocumentoDAO tipoDocumentoDAO;

	public TipoDocumentoDAO getTipoDocumentoDAO() {
		return tipoDocumentoDAO;
	}

	public void setTipoDocumentoDAO(TipoDocumentoDAO tipoDocumentoDAO) {
		this.tipoDocumentoDAO = tipoDocumentoDAO;
	}

	@Transactional
	public void saveTipoDocumento(TipoDocumento doc) {
		tipoDocumentoDAO.saveTipoDocumento(doc);

	}

	@Transactional
	public void deleteTipoDocumento(TipoDocumento doc) {
		tipoDocumentoDAO.deleteTipoDocumento(doc);

	}

	@Transactional(readOnly = true)
	public List<TipoDocumento> getAllTipoDocumento() {

		return tipoDocumentoDAO.getAllTipoDocumento();
	}

	@Transactional(readOnly = true)
	public TipoDocumento getByID(Integer id) {

		return tipoDocumentoDAO.getByID(id);
	}

}
