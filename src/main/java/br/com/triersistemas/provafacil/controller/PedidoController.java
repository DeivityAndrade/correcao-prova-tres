package br.com.triersistemas.provafacil.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.triersistemas.provafacil.armazenamento.SalvaDados;
import br.com.triersistemas.provafacil.model.ItemPedidoModel;
import br.com.triersistemas.provafacil.model.PedidoModel;
import br.com.triersistemas.provafacil.model.ProdutoModel;
import br.com.triersistemas.provafacil.model.EnumStatusPedidoModel;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@GetMapping("/cadastrar")
	public PedidoModel CadastrarPedido() {
		PedidoModel addPedido = new PedidoModel();
		SalvaDados.pedido.add(addPedido);
		return addPedido;
	}

	@GetMapping("/adicionar")
	public PedidoModel addProduto(@RequestParam(value = "id-pedido") Long id_pedido, 
	@RequestParam(value = "id-produto") Long id_produto,
	@RequestParam Integer qtd) {
		for (PedidoModel pedidos : SalvaDados.pedido) {
			if (id_pedido.equals(pedidos.getId())) {
				for (ProdutoModel produtos: SalvaDados.produto) {
					if (id_produto.equals(produtos.getId())) {
						pedidos.addProduto(produtos, qtd);
						return pedidos;						
					}
					
				}
			}
		}
		return null;
	}
	@GetMapping("/retirar")
	public PedidoModel removerItemProduto(@RequestParam(value = "id") Long id_item) {
		for (PedidoModel pedido : SalvaDados.pedido) {
			for (ItemPedidoModel item : pedido.getItens()) {
				if (id_item.equals(item.getId())) {
					pedido.retirarItemPedido(item);
					return pedido;
				}				
			}
		}
		
		return null;
	}
	
	@GetMapping("/listar")
	public List<PedidoModel> ListarPedidos(){
		return SalvaDados.pedido;
	}
	@GetMapping("/pagar")
	public PedidoModel confirmarPagamento(@RequestParam Long id) {
		for (PedidoModel pedidos : SalvaDados.pedido) {
			if (id.equals(pedidos.getId())) {
				pedidos.finalizar();
				return pedidos;
			}
		}
		return null;
	}
	@GetMapping("/excluir")
	public PedidoModel excluirPedido(@RequestParam Long id) {
		for (PedidoModel pedidos : SalvaDados.pedido) {
			if (id.equals(pedidos.getId())) {
				if(EnumStatusPedidoModel.AGUARDANDO_PAGAMENTO.equals(pedidos.getStatus())) {
					SalvaDados.pedido.remove(pedidos);
					return pedidos;
				}
			} else {
				throw new RuntimeException("Já está pago");				
			}
		}
		return null;
	}

}


