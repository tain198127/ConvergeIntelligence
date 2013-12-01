/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tain198127
 */
@Entity
@Table(name = "article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a WHERE a.id = :id"),
    @NamedQuery(name = "Article.findByArticleName", query = "SELECT a FROM Article a WHERE a.articleName = :articleName"),
    @NamedQuery(name = "Article.findByAuthor", query = "SELECT a FROM Article a WHERE a.author = :author"),
    @NamedQuery(name = "Article.findByIsbn", query = "SELECT a FROM Article a WHERE a.isbn = :isbn"),
    @NamedQuery(name = "Article.findByEmail", query = "SELECT a FROM Article a WHERE a.email = :email"),
    @NamedQuery(name = "Article.findByVersion", query = "SELECT a FROM Article a WHERE a.version = :version"),
    @NamedQuery(name = "Article.findByPublisheVendor", query = "SELECT a FROM Article a WHERE a.publisheVendor = :publisheVendor"),
    @NamedQuery(name = "Article.findByOriginalLang", query = "SELECT a FROM Article a WHERE a.originalLang = :originalLang"),
    @NamedQuery(name = "Article.findByDocType", query = "SELECT a FROM Article a WHERE a.docType = :docType"),
    @NamedQuery(name = "Article.findByPublishDate", query = "SELECT a FROM Article a WHERE a.publishDate = :publishDate")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "ARTICLE_NAME")
    private String articleName;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "PUBLISHE_VENDOR")
    private String publisheVendor;
    @Basic(optional = false)
    @Column(name = "ORIGINAL_LANG")
    private String originalLang;
    @Basic(optional = false)
    @Column(name = "DOC_TYPE")
    private String docType;
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    public Article() {
    }

    public Article(BigDecimal id) {
        this.id = id;
    }

    public Article(BigDecimal id, String articleName, String originalLang, String docType) {
        this.id = id;
        this.articleName = articleName;
        this.originalLang = originalLang;
        this.docType = docType;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublisheVendor() {
        return publisheVendor;
    }

    public void setPublisheVendor(String publisheVendor) {
        this.publisheVendor = publisheVendor;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public void setOriginalLang(String originalLang) {
        this.originalLang = originalLang;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "www.convergeintelligence.Article[ id=" + id + " ]";
    }
    
}
