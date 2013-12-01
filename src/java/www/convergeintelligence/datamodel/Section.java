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
@Table(name = "section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findByGuid", query = "SELECT s FROM Section s WHERE s.guid = :guid"),
    @NamedQuery(name = "Section.findBySectionNo", query = "SELECT s FROM Section s WHERE s.sectionNo = :sectionNo"),
    @NamedQuery(name = "Section.findBySectionName", query = "SELECT s FROM Section s WHERE s.sectionName = :sectionName"),
    @NamedQuery(name = "Section.findByFkChapterId", query = "SELECT s FROM Section s WHERE s.fkChapterId = :fkChapterId")})
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GUID")
    private String guid;
    @Basic(optional = false)
    @Column(name = "SECTION_NO")
    private int sectionNo;
    @Basic(optional = false)
    @Column(name = "SECTION_NAME")
    private String sectionName;
    @Basic(optional = false)
    @Column(name = "FK_CHAPTER_ID")
    private BigInteger fkChapterId;

    public Section() {
    }

    public Section(String guid) {
        this.guid = guid;
    }

    public Section(String guid, int sectionNo, String sectionName, BigInteger fkChapterId) {
        this.guid = guid;
        this.sectionNo = sectionNo;
        this.sectionName = sectionName;
        this.fkChapterId = fkChapterId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guid != null ? guid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "www.convergeintelligence.Section[ guid=" + guid + " ]";
    }
    
}
