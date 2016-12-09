package ru.kuramov.model;
// Generated 05.12.2016 13:39:36 by Hibernate Tools 5.2.0.Beta1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Section generated by hbm2java
 */
@Entity
@Table(name="Section", catalog="Catalog_IDEA")
public class Section  implements java.io.Serializable {

    private Integer idSection;
    private Edition edition;
    private String sectionname;
    private Set<Article> articles = new HashSet<Article>(0);

    public Section() {
    }

	
    public Section(Edition edition) {
        this.edition = edition;
    }
    public Section(Edition edition, String sectionname, Set<Article> articles) {
       this.edition = edition;
       this.sectionname = sectionname;
       this.articles = articles;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idSection", unique=true, nullable=false)
    public Integer getIdSection() {
        return this.idSection;
    }
    
    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Edition_idEdition", nullable=false)
    public Edition getEdition() {
        return this.edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    
    @Column(name="sectionname", length=50)
    public String getSectionname() {
        return this.sectionname;
    }
    
    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="section")
    public Set<Article> getArticles() {
        return this.articles;
    }
    
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

}

