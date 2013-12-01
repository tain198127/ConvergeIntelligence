/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.datamodel;

import java.io.Serializable;
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
@Table(name = "translation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Translation.findAll", query = "SELECT t FROM Translation t"),
    @NamedQuery(name = "Translation.findByGuid", query = "SELECT t FROM Translation t WHERE t.guid = :guid"),
    @NamedQuery(name = "Translation.findByFkUserId", query = "SELECT t FROM Translation t WHERE t.fkUserId = :fkUserId"),
    @NamedQuery(name = "Translation.findByFkParagraphGuid", query = "SELECT t FROM Translation t WHERE t.fkParagraphGuid = :fkParagraphGuid"),
    @NamedQuery(name = "Translation.findByUpRank", query = "SELECT t FROM Translation t WHERE t.upRank = :upRank"),
    @NamedQuery(name = "Translation.findByDownRank", query = "SELECT t FROM Translation t WHERE t.downRank = :downRank")})
public class Translation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GUID")
    private String guid;
    @Basic(optional = false)
    @Column(name = "FK_USER_ID")
    private BigInteger fkUserId;
    @Basic(optional = false)
    @Column(name = "FK_PARAGRAPH_GUID")
    private String fkParagraphGuid;
    @Basic(optional = false)
    @Column(name = "UP_RANK")
    private BigInteger upRank;
    @Basic(optional = false)
    @Column(name = "DOWN_RANK")
    private BigInteger downRank;

    public Translation() {
    }

    public Translation(String guid) {
        this.guid = guid;
    }

    public Translation(String guid, BigInteger fkUserId, String fkParagraphGuid, BigInteger upRank, BigInteger downRank) {
        this.guid = guid;
        this.fkUserId = fkUserId;
        this.fkParagraphGuid = fkParagraphGuid;
        this.upRank = upRank;
        this.downRank = downRank;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public BigInteger getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(BigInteger fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getFkParagraphGuid() {
        return fkParagraphGuid;
    }

    public void setFkParagraphGuid(String fkParagraphGuid) {
        this.fkParagraphGuid = fkParagraphGuid;
    }

    public BigInteger getUpRank() {
        return upRank;
    }

    public void setUpRank(BigInteger upRank) {
        this.upRank = upRank;
    }

    public BigInteger getDownRank() {
        return downRank;
    }

    public void setDownRank(BigInteger downRank) {
        this.downRank = downRank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guid != null ? guid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Translation)) {
            return false;
        }
        Translation other = (Translation) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "www.convergeintelligence.Translation[ guid=" + guid + " ]";
    }
    
}
