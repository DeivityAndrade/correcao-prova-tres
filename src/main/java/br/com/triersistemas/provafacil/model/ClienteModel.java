package br.com.triersistemas.provafacil.model;

public class ClienteModel extends PessoaModel {

	private String sintoma;

	public ClienteModel(String nome, String documento,String sintoma) {
		super(nome, documento);
		this.sintoma = sintoma;
	}   

	public String getSintoma() {
		return sintoma;

	}
}

