package br.com.caelum.livraria.service;

import org.javamoney.moneta.Money;

import br.com.caelum.livraria.dominio.CalculadoraFrete;
import br.com.caelum.livraria.dominio.CarrinhoDeCompras;
import br.com.caelum.livraria.dominio.Cliente;
import br.com.caelum.livraria.dominio.ISBN;
import br.com.caelum.livraria.dominio.Livro;
import br.com.caelum.livraria.dominio.TodosLivros;

public class SelecaoDeLivro {
	
	private final TodosLivros todosLivros;
	private final CalculadoraFrete calculadoraFrete;
	private final CarrinhoDeComprasFactory carrinhoFactory;
	
	
	public SelecaoDeLivro(TodosLivros todosLivros, CalculadoraFrete calculadoraFrete) {
		this.todosLivros = todosLivros;
		this.calculadoraFrete = calculadoraFrete;
		this.carrinhoFactory = new CarrinhoDeComprasFactory();
	}

	public CarrinhoDeCompras adicionarLivroNoCarrinhoDoCliente(ISBN isbn, Cliente cliente) {
		Livro livro = todosLivros.por(isbn);
		Money valorFrete = calculadoraFrete.baseadoEm(cliente.getCep());
		
		CarrinhoDeCompras carrinho = carrinhoFactory.obterCarrinho(cliente, livro, valorFrete);
		return carrinho;
	}


}
