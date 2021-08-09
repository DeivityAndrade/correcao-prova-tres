package br.com.triersistemas.provafacil.model;

import java.math.BigDecimal;

public class ItemPedidoModel {

	private Long id;
	private ProdutoModel produto;
	private Integer qtd;
	private BigDecimal valorTotal;

	private static long gerador = 0;

	public ItemPedidoModel(ProdutoModel produto, Integer qtd) {
		this.id = gerador++;
		this.qtd = qtd;
		this.produto = produto;
		this.valorTotal = produto.getValor().multiply(BigDecimal.valueOf(qtd));
	}

	public Long getId() {
		return id;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public Integer getQtd() {
		return qtd;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
}

