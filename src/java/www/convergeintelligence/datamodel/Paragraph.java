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
@Table(name = "paragraph")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paragraph.findAll", query = "SELECT p FROM Paragraph p"),
    @NamedQuery(name = "Paragraph.findByGuid", query = "SELECT p FROM Paragraph p WHERE p.guid = :guid"),
    @NamedQuery(name = "Paragraph.findByParagraphNo", query = "SELECT p FROM Paragraph p WHERE p.paragraphNo = :paragraphNo"),
    @NamedQuery(name = "Paragraph.findByFkSectionGuid", query = "SELECT p FROM Paragraph p WHERE p.fkSectionGuid = :fkSectionGuid")})
public class Paragraph implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GUID")
    private String guid;
    @Basic(optional = false)
    @Column(name = "PARAGRAPH_NO")
    private BigInteger paragraphNo;
    @Basic(optional = false)
    @Column(name = "FK_SECTION_GUID")
    private String fkSectionGuid;

    public Paragraph() {
    }

    public Paragraph(String guid) {
        this.guid = guid;
    }

    public Paragraph(String guid, BigInteger paragraphNo, String fkSectionGuid) {
        this.guid = guid;
        this.paragraphNo = paragraphNo;
        this.fkSectionGuid = fkSectionGuid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guid != null ? guid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paragraph)) {
            return false;
        }
        Paragraph other = (Paragraph) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "www.convergeintelligence.Paragraph[ guid=" + guid + " ]";
    }
    
}
