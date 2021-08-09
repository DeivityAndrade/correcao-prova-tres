package br.com.triersistemas.provafacil.armazenamento;

import java.util.ArrayList;
import java.util.List;

import br.com.triersistemas.provafacil.model.PedidoModel;
import br.com.triersistemas.provafacil.model.PessoaModel;
import br.com.triersistemas.provafacil.model.ProdutoModel;



public class SalvaDados {
	
	private SalvaDados() {}
	
	public static List<PessoaModel> pessoa = new ArrayList<PessoaModel>();
	public static List<ProdutoModel> produto = new ArrayList<ProdutoModel>();
	public static List <PedidoModel> pedido = new ArrayList<PedidoModel>();
	
	

}
