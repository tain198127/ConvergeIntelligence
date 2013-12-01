/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.datamodel;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "article_detail_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticleDetailView.findAll", query = "SELECT a FROM ArticleDetailView a"),
    @NamedQuery(name = "ArticleDetailView.findByArticleId", query = "SELECT a FROM ArticleDetailView a WHERE a.articleId = :articleId"),
    @NamedQuery(name = "ArticleDetailView.findByArticleName", query = "SELECT a FROM ArticleDetailView a WHERE a.articleName = :articleName"),
    @NamedQuery(name = "ArticleDetailView.findByAuthor", query = "SELECT a FROM ArticleDetailView a WHERE a.author = :author"),
    @NamedQuery(name = "ArticleDetailView.findByIsbn", query = "SELECT a FROM ArticleDetailView a WHERE a.isbn = :isbn"),
    @NamedQuery(name = "ArticleDetailView.findByEmail", query = "SELECT a FROM ArticleDetailView a WHERE a.email = :email"),
    @NamedQuery(name = "ArticleDetailView.findByVersion", query = "SELECT a FROM ArticleDetailView a WHERE a.version = :version"),
    @NamedQuery(name = "ArticleDetailView.findByPublisheVendor", query = "SELECT a FROM ArticleDetailView a WHERE a.publisheVendor = :publisheVendor"),
    @NamedQuery(name = "ArticleDetailView.findByOriginalLang", query = "SELECT a FROM ArticleDetailView a WHERE a.originalLang = :originalLang"),
    @NamedQuery(name = "ArticleDetailView.findByDocType", query = "SELECT a FROM ArticleDetailView a WHERE a.docType = :docType"),
    @NamedQuery(name = "ArticleDetailView.findByPublishDate", query = "SELECT a FROM ArticleDetailView a WHERE a.publishDate = :publishDate"),
    @NamedQuery(name = "ArticleDetailView.findByChapterId", query = "SELECT a FROM ArticleDetailView a WHERE a.chapterId = :chapterId"),
    @NamedQuery(name = "ArticleDetailView.findByChapterNo", query = "SELECT a FROM ArticleDetailView a WHERE a.chapterNo = :chapterNo"),
    @NamedQuery(name = "ArticleDetailView.findByChapterName", query = "SELECT a FROM ArticleDetailView a WHERE a.chapterName = :chapterName"),
    @NamedQuery(name = "ArticleDetailView.findByFkArticleId", query = "SELECT a FROM ArticleDetailView a WHERE a.fkArticleId = :fkArticleId"),
    @NamedQuery(name = "ArticleDetailView.findByParagraphGuid", query = "SELECT a FROM ArticleDetailView a WHERE a.paragraphGuid = :paragraphGuid"),
    @NamedQuery(name = "ArticleDetailView.findByParagraphNo", query = "SELECT a FROM ArticleDetailView a WHERE a.paragraphNo = :paragraphNo"),
    @NamedQuery(name = "ArticleDetailView.findByFkSectionGuid", query = "SELECT a FROM ArticleDetailView a WHERE a.fkSectionGuid = :fkSectionGuid"),
    @NamedQuery(name = "ArticleDetailView.findBySectionGuid", query = "SELECT a FROM ArticleDetailView a WHERE a.sectionGuid = :sectionGuid"),
    @NamedQuery(name = "ArticleDetailView.findBySectionNo", query = "SELECT a FROM ArticleDetailView a WHERE a.sectionNo = :sectionNo"),
    @NamedQuery(name = "ArticleDetailView.findBySectionName", query = "SELECT a FROM ArticleDetailView a WHERE a.sectionName = :sectionName"),
    @NamedQuery(name = "ArticleDetailView.findByFkChapterId", query = "SELECT a FROM ArticleDetailView a WHERE a.fkChapterId = :fkChapterId")})
public class ArticleDetailView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ARTICLE_ID")
    private BigInteger articleId;
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
    @Column(name = "ORIGINAL_LANG")
    private String originalLang;
    @Column(name = "DOC_TYPE")
    private String docType;
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Column(name = "CHAPTER_ID")
    private BigInteger chapterId;
    @Column(name = "CHAPTER_NO")
    private Integer chapterNo;
    @Column(name = "CHAPTER_NAME")
    private String chapterName;
    @Column(name = "FK_ARTICLE_ID")
    private BigInteger fkArticleId;
    @Basic(optional = false)
    @Column(name = "PARAGRAPH_GUID")
    @Id
    private String paragraphGuid;
    @Basic(optional = false)
    @Column(name = "PARAGRAPH_NO")
    private BigInteger paragraphNo;
    @Basic(optional = false)
    @Column(name = "FK_SECTION_GUID")
    private String fkSectionGuid;
    @Column(name = "SECTION_GUID")
    private String sectionGuid;
    @Column(name = "SECTION_NO")
    private Integer sectionNo;
    @Column(name = "SECTION_NAME")
    private String sectionName;
    @Column(name = "FK_CHAPTER_ID")
    private BigInteger fkChapterId;

    public ArticleDetailView() {
    }

    public BigInteger getArticleId() {
        return articleId;
    }

    public void setArticleId(BigInteger articleId) {
        this.articleId = articleId;
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

    public BigInteger getChapterId() {
        return chapterId;
    }

    public void setChapterId(BigInteger chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
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

    public String getParagraphGuid() {
        return paragraphGuid;
    }

    public void setParagraphGuid(String paragraphGuid) {
        this.paragraphGuid = paragraphGuid;
    }

    public BigInteger getParagraphNo() {
        return paragraphNo;
    }

    public void setParagraphNo(BigInteger paragraphNo) {
        this.paragraphNo = paragraphNo;
    }

    public String getFkSectionGuid() {
        return fkSectionGuid;
    }

    public void setFkSectionGuid(String fkSectionGuid) {
        this.fkSectionGuid = fkSectionGuid;
    }

    public String getSectionGuid() {
        return sectionGuid;
    }

    public void setSectionGuid(String sectionGuid) {
        this.sectionGuid = sectionGuid;
    }

    public Integer getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(Integer sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public BigInteger getFkChapterId() {
        return fkChapterId;
    }

    public void setFkChapterId(BigInteger fkChapterId) {
        this.fkChapterId = fkChapterId;
    }
    
}
