package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProductDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void save(Product product) {
		em.persist(product);
	}

	public List<Product> list() {
		return em
			.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class)
			.getResultList();
	}
}
