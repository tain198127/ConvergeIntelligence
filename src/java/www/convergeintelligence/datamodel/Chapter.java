/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tain198127
 */
@Entity
@Table(name = "chapter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c"),
    @NamedQuery(name = "Chapter.findById", query = "SELECT c FROM Chapter c WHERE c.id = :id"),
    @NamedQuery(name = "Chapter.findByChapterNo", query = "SELECT c FROM Chapter c WHERE c.chapterNo = :chapterNo"),
    @NamedQuery(name = "Chapter.findByChapterName", query = "SELECT c FROM Chapter c WHERE c.chapterName = :chapterName"),
    @NamedQuery(name = "Chapter.findByFkArticleId", query = "SELECT c FROM Chapter c WHERE c.fkArticleId = :fkArticleId")})
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "CHAPTER_NO")
    private int chapterNo;
    @Basic(optional = false)
    @Column(name = "CHAPTER_NAME")
    private String chapterName;
    @Basic(optional = false)
    @Column(name = "FK_ARTICLE_ID")
    private BigInteger fkArticleId;

    public Chapter() {
    }

    public Chapter(BigDecimal id) {
        this.id = id;
    }

    public Chapter(BigDecimal id, int chapterNo, String chapterName, BigInteger fkArticleId) {
        this.id = id;
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.fkArticleId = fkArticleId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public BigInteger getFkArticleId() {
        return fkArticleId;
    }

    public void setFkArticleId(BigInteger fkArticleId) {
        this.fkArticleId = fkArticleId;
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
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "www.convergeintelligence.Chapter[ id=" + id + " ]";
    }
    
}
