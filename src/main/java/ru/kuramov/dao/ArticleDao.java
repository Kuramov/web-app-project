package ru.kuramov.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import ru.kuramov.model.Article;
import ru.kuramov.model.Catalog;
import ru.kuramov.util.HibernateUtil;

/**
 * DAO for Article
 */
@Stateless
public class ArticleDao {
//	@PersistenceContext(unitName = "web-app-project-persistence-unit")
//	private EntityManager em;

	private Session session;

	@PostConstruct
	private void init() {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> HIBERNATE OPEN SESSION");
		} catch (Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}
	}

	@PreDestroy
	private void destroy() {
		session.close();
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<< HIBERNATE CLOSE SESSION");
	}

	public List<Catalog> getCatalogs() {
		Query catalogHql = session.createQuery("from ru.kuramov.model.Catalog");
		List<Catalog> catalogs = (List<Catalog>)catalogHql.list();
		catalogs.forEach(catalog -> Hibernate.initialize(catalog.getEditions()));
//		//for(Catalog c : catalogs) {
//		//	Hibernate.initialize(c.getEditions());
//		//}
		return catalogs;
	}

	public void updateCatalog(String journal, int id) {
		Catalog catalog = (Catalog) session.get(Catalog.class, id);

		System.out.println("==================== id = "+id+" string = "+journal+" catalog = "
				+catalog.getJornal());

		if (catalog != null) {
			catalog.setJornal(journal);
			session.saveOrUpdate(catalog);
			session.flush();
			System.out.println("==================== UPDATE id = "+id+" catalog = "
					+catalog.getJornal());
		}

	}

	public void saveCatalog(Catalog catalog) {
		session.save(catalog);
	}

	public void deleteCatalog(int id) {
		Catalog catalog = (Catalog) session.get(Catalog.class, id);

		System.out.println("==================== id = "+id+" catalog = "
				+catalog.getJornal());

		if (catalog != null) {
			session.delete(catalog);
			session.flush();
			System.out.println("==================== DELETE id = "+id+" catalog = "
					+catalog.getJornal());
		}

	}

//	public void create(Article entity) {
//		em.persist(entity);
//	}
//
//	public void deleteById(Integer id) {
//		Article entity = em.find(Article.class, id);
//		if (entity != null) {
//			em.remove(entity);
//		}
//
//
//	}
//
//	public Article findById(Integer id) {
//		return em.find(Article.class, id);
//	}
//
//	public Article update(Article entity) {
//		return em.merge(entity);
//	}
//
//	public List<Article> listAll(Integer startPosition, Integer maxResult) {
//		TypedQuery<Article> findAllQuery = em
//				.createQuery(
//						"SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.section ORDER BY a.idArticle",
//						Article.class);
//		if (startPosition != null) {
//			findAllQuery.setFirstResult(startPosition);
//		}
//		if (maxResult != null) {
//			findAllQuery.setMaxResults(maxResult);
//		}
//		return findAllQuery.getResultList();
//	}
}
