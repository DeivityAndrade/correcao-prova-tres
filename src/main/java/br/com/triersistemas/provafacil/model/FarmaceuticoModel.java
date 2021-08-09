package br.com.triersistemas.provafacil.model;

public class FarmaceuticoModel extends PessoaModel {

	private String pis;

	public FarmaceuticoModel(String nome, String documento,String pis) {
		super(nome, documento);
		this.pis = pis;
	}   

	public String getPis() {
		return pis;

	}
}
