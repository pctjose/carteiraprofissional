package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.TipoDocumento;

public interface TipoDocumentoDAO {
	public void saveTipoDocumento(TipoDocumento doc);
	public void deleteTipoDocumento(TipoDocumento doc);
	public List<TipoDocumento> getAllTipoDocumento();
	public TipoDocumento getByID(Integer id);
	

}
