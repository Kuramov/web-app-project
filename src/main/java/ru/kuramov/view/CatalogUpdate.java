package ru.kuramov.view;

/**
 * Created by valerii on 08.12.16.
 */

import ru.kuramov.dao.ArticleDao;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CatalogUpdate {

    @Inject
    private ArticleDao articleDao;

    private int id;
    private String journal;

    public CatalogUpdate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void update() {
        articleDao.updateCatalog(journal, id);
    }

    public void deleteCatalog() {
        articleDao.deleteCatalog(id);
    }
}
