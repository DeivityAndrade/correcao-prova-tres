package br.com.triersistemas.provafacil.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.triersistemas.provafacil.armazenamento.SalvaDados;
import br.com.triersistemas.provafacil.model.ClienteModel;
import br.com.triersistemas.provafacil.model.FarmaceuticoModel;
import br.com.triersistemas.provafacil.model.PessoaModel;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@GetMapping("/cadastrar-farmaceutico")
	public FarmaceuticoModel cadastrarFarmaceutico(@RequestParam String nome, @RequestParam String documento, @RequestParam String pis) {
		FarmaceuticoModel f = new FarmaceuticoModel(nome, documento, pis);
		SalvaDados.pessoa.add(f);
		return f;
	}
	
	@GetMapping("/cadastrar-cliente")
	public ClienteModel cadastrarCliente(@RequestParam String nome, @RequestParam String documento, @RequestParam String sintoma) {
		ClienteModel c = new ClienteModel(nome, documento, sintoma);
		SalvaDados.pessoa.add(c);
		return c;
	}	
	
	@GetMapping("/listar")
	public List<PessoaModel> listarPessoa() {
		return SalvaDados.pessoa;
	}
	
	@GetMapping("/excluir")
	public PessoaModel excluirPessoa(@RequestParam Long id) {
		for (PessoaModel p : SalvaDados.pessoa) {
			if (id.equals(p.getId())) {
				SalvaDados.pessoa.remove(p);
				return p;
			}
		}
		return null;
	}
	
	 private String gerarCpf(final String preDocumento) {

        String preDoc = preDocumento;
        char[] numeros = preDoc.toCharArray();
        Integer soma = 0;

        for (int i = numeros.length; i > 0; i--) {
            Integer num = Integer.valueOf(String.valueOf(numeros[i - 1]));
            soma += (num * i);
        }
        Integer dv1 = soma % 11;

        preDoc = preDoc + (dv1.equals(10) ? 0 : dv1);
        numeros = preDoc.toCharArray();
        soma = 0;

        for (int i = numeros.length - 1; i >= 0; i--) {
            Integer num = Integer.valueOf(String.valueOf(numeros[i]));
            soma += (num * i);
        }
        Integer dv2 = soma % 11;

        return preDoc + (dv2.equals(10) ? 0 : dv2);
    }
	
	public void checarListaPessoas() {
		if(Objects.isNull(SalvaDados.pessoa) || SalvaDados.pessoa.size() < 1) {
			throw new RuntimeException("N??o h?? nenhuma pessoa cadastrada!");
		}
	}
	
	public PessoaModel procurarPessoa(Long id) {
		checarListaPessoas();
		for(PessoaModel p: SalvaDados.pessoa) {
			if (id.equals(p.getId())) {
				return p;
			}
		}
		
		throw new RuntimeException("Pessoa inexistente!");
	}
	
	@GetMapping("/validar-documento")
	public String validarCPF(@RequestParam(value = "id") Long id) {
		PessoaModel p = procurarPessoa(id);
		
        if (Objects.isNull(p.getDocumento())
                || p.getDocumento().length() != 11) {
        	return "Documento inv??lido";
        }
        
        String preDoc = p.getDocumento().substring(0, 9);
        String cpfValido = this.gerarCpf(preDoc);
        if(p.getDocumento().equals(cpfValido)) {
			
			//Retornar documento formatado
			String cpfFormatado = "";
			
			for (int i = 0; i < 11; i++) {
				if (i < 3) {
					cpfFormatado += p.getDocumento().charAt(i);
				} else if (i == 3) {
					cpfFormatado += ".";
					cpfFormatado += p.getDocumento().charAt(i);
				} else if (i < 6) {
					cpfFormatado += p.getDocumento().charAt(i);
				} else if (i == 6) {
					cpfFormatado += ".";
					cpfFormatado += p.getDocumento().charAt(i);
				} else if (i < 9) {
					cpfFormatado += p.getDocumento().charAt(i);
				} else if (i == 9) {
					cpfFormatado += "-";
					cpfFormatado += p.getDocumento().charAt(i);
				} else {
					cpfFormatado += p.getDocumento().charAt(i);
				}
			}
			
			return cpfFormatado;
		}
		
		return "Documento inv??lido";
	}
}
