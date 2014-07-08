package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.TipoDocumento;

public interface TipoDocumentoService {

	public void saveTipoDocumento(TipoDocumento doc);
	public void deleteTipoDocumento(TipoDocumento doc);
	public List<TipoDocumento> getAllTipoDocumento();
	public TipoDocumento getByID(Integer id);
}
