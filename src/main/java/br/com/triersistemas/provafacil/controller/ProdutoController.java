package br.com.triersistemas.provafacil.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.triersistemas.provafacil.armazenamento.SalvaDados;
import br.com.triersistemas.provafacil.model.EnumTipoProdutoModel;
import br.com.triersistemas.provafacil.model.ProdutoModel;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@GetMapping("/cadastrar")
	public ProdutoModel CadastrarProduto(
			@RequestParam String nome,
			@RequestParam EnumTipoProdutoModel tipo,
			@RequestParam BigDecimal valor
			) {
		ProdutoModel addProduto = new ProdutoModel(nome,valor,tipo);
		SalvaDados.produto.add(addProduto);
		return addProduto;
	}
	@GetMapping("/alterar")
	public ProdutoModel alterarProduto(@RequestParam Long id, 
			@RequestParam String nome, 
			@RequestParam  BigDecimal valor, 
			@RequestParam EnumTipoProdutoModel tipo) {
		for (ProdutoModel p : SalvaDados.produto) {
			if (id.equals(p.getId())) {
				p.alterar(nome, valor, tipo);
				return p;
			}
		}
		return null;
	}
	

	@GetMapping("/listar")
	public List<ProdutoModel> ListarProduto(){
		return SalvaDados.produto;
	}

	@GetMapping("excluir")
	public ProdutoModel excluirProduto(@RequestParam Long id) {
		for (ProdutoModel p : SalvaDados.produto) {
			if (id.equals(p.getId())) {
				SalvaDados.produto.remove(p);
				return p;
			}
		}
		return null;
	}
	
}
