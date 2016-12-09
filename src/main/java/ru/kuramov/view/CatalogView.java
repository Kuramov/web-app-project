package ru.kuramov.view;

import ru.kuramov.dao.ArticleDao;
import ru.kuramov.model.Catalog;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by valerii on 08.12.16.
 */
@Model
public class CatalogView {

    @Inject
    private ArticleDao articleDao;

    private Catalog catalog;

    public CatalogView() {
    }

    @PostConstruct
    private void  init() {
        catalog = new Catalog();
    }

    @Named
    @Produces
    @RequestScoped
    private Catalog getNewCatalog() {
        return catalog;
    }

    public  void save() {
        System.out.println("==================== "+catalog.getJornal());
        articleDao.saveCatalog(catalog);
    }

    public List<Catalog> getCatalogs() {
        return articleDao.getCatalogs();
    }



}
