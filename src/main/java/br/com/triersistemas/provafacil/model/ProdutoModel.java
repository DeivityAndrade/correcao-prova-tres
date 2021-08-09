package br.com.triersistemas.provafacil.model;

import java.math.BigDecimal;

public class ProdutoModel {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private EnumTipoProdutoModel tipo;

	private static  long gerador = 0;

	public ProdutoModel(String nome, BigDecimal valor, EnumTipoProdutoModel tipo) {
		this.id = ++gerador;
		this.nome = nome;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public EnumTipoProdutoModel getTipo() {
		return tipo;
	}

	public static long getGerador() {
		return gerador;
	}
	public void alterar(String nome,BigDecimal valor,EnumTipoProdutoModel tipo) {
		this.nome=nome;
		this.valor=valor;
		this.tipo=tipo;
	}
	public void remover(Long id) {
		this.id=id;
	}

}
