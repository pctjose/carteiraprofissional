package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.CartaoProfissional;
import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.Membro;
import org.apm.carteiraprofissional.SubCategoria;

public interface MembroDAO {
	
	public void saveMembro(Membro membro);

    public Membro getMembroByID(Integer id);

    public Membro getMembroByUUID(String uuid);

    public Membro getMembroByBI(String bi);

    public Membro getMembroByInss(String inss);

    public Membro getMembroByNumeroCartao(String numeroCartao);

    public Membro getMembroByCartao(CartaoProfissional cartao);

    public List<Membro> getAllMembros();

    public List<Membro> getAllByAttributes(SubCategoria profissao, Escolaridade escolaridade, String nome, Empregador empregador);


}
