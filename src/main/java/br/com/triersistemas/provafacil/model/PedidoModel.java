package br.com.triersistemas.provafacil.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class PedidoModel {


	private Long id;
	private EnumStatusPedidoModel status;
	private LocalDateTime dataPagamento;
	private LocalDateTime dataPedido;
	private List<ItemPedidoModel> itens;
	
	private static  long gerador = 0;

	public PedidoModel() {
		this.itens = new ArrayList<ItemPedidoModel>();
		this.id = gerador++;
		this.status = EnumStatusPedidoModel.AGUARDANDO_PAGAMENTO;
		this.dataPedido = LocalDateTime.now();
	}

	public void addProduto(ProdutoModel produto, Integer qtd) {
		if (status.equals(EnumStatusPedidoModel.PAGO)) {
			throw new RuntimeException("Pedido finalizado");
		} else {
			this.itens.add(new ItemPedidoModel(produto, qtd));
		}
	}
	
	public void finalizar() {
		if (status.equals(EnumStatusPedidoModel.PAGO)) {
			throw new RuntimeException("Pedido jÃ¡ estÃ¡ pago");
		} else {
			this.dataPagamento = LocalDateTime.now();
			this.status = EnumStatusPedidoModel.PAGO;
		}
	}

	public Long getId() {
		return id;
	}

	public EnumStatusPedidoModel getStatus() {
		return status;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public List<ItemPedidoModel> getItens() {
		return itens;
	}
	
	public BigDecimal getValorTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for (ItemPedidoModel i : itens) {
			soma = soma.add(i.getValorTotal());
		}
		return soma;
	}

	public ItemPedidoModel retirarItemPedido(ItemPedidoModel itemPedido) {
		this.itens.remove(itemPedido);
		return itemPedido;
	}

}

