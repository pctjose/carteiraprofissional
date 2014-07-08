package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.CartaoProfissional;
import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.Membro;
import org.apm.carteiraprofissional.SubCategoria;
import org.apm.carteiraprofissional.dao.MembroDAO;
import org.apm.carteiraprofissional.service.MembroService;

public class MembroServiceImpl implements MembroService {

	private MembroDAO membroDAO;

	public MembroDAO getMembroDAO() {
		return membroDAO;
	}

	public void setMembroDAO(MembroDAO membroDAO) {
		this.membroDAO = membroDAO;
	}

	
	public void saveMembro(Membro membro) {
		membroDAO.saveMembro(membro);
	}

	
	public Membro getMembroByID(Integer id) {
		return membroDAO.getMembroByID(id);
	}

	
	public Membro getMembroByUUID(String uuid) {
		return membroDAO.getMembroByUUID(uuid);
	}

	
	public Membro getMembroByBI(String bi) {
		return membroDAO.getMembroByBI(bi);
	}

	
	public Membro getMembroByInss(String inss) {
		return membroDAO.getMembroByInss(inss);
	}

	
	public Membro getMembroByNumeroCartao(String numeroCartao) {
		return membroDAO.getMembroByNumeroCartao(numeroCartao);
	}

	
	public Membro getMembroByCartao(CartaoProfissional cartao) {
		return membroDAO.getMembroByCartao(cartao);
	}

	
	public List<Membro> getAllMembros() {
		return membroDAO.getAllMembros();
	}

	
	public List<Membro> getAllByAttributes(SubCategoria profissao,
			Escolaridade escolaridade, String nome, Empregador empregador) {
		return membroDAO.getAllByAttributes(profissao, escolaridade, nome,
				empregador);
	}

	
	public List<Membro> getAllOfProfissao(SubCategoria profissao) {
		return membroDAO.getAllByAttributes(profissao, null, null, null);
	}

	
	public List<Membro> getAllOfEscolaridade(Escolaridade escolaridade) {
		return membroDAO.getAllByAttributes(null, escolaridade, null, null);
	}

	
	public List<Membro> getAllOfNome(String nome) {
		return membroDAO.getAllByAttributes(null, null, nome, null);
	}

	
	public List<Membro> getAllOfEmpregador(Empregador empregador) {
		return membroDAO.getAllByAttributes(null, null, null, empregador);
	}

}
