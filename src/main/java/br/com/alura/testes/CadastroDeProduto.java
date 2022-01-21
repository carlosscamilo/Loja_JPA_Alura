package br.com.alura.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDAO;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.model.Categoria;
import br.com.alura.model.Produto;
import br.com.alura.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		Produto p = produtoDAO.buscarPorId(1l);
		System.out.println(p.getPreco());
	
		List<Produto> todos = produtoDAO.buscarPorNomeDaCategoria("celulares");
		
		todos.forEach(p2 -> System.out.println(p2.getNome()));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("celulares");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);

		Produto celular = new Produto("Iphone 13", "Celular da apple", new BigDecimal("500"), celulares);

		em.getTransaction().begin();
		categoriaDAO.cadastrar(celulares);
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}

}
