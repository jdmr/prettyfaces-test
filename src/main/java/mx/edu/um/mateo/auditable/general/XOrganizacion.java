package mx.edu.um.mateo.auditable.general;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.edu.um.mateo.general.modelo.Organizacion;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "xorganizaciones")
public class XOrganizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private long version;
    @Basic(optional = false)
    @Column(name = "organizacion_id", nullable = false)
    private long organizacionId;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 6)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "nombre_completo", nullable = false, length = 128)
    private String nombreCompleto;
    @Basic(optional = false)
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;
    @Column(name = "curp", length = 18)
    private String curp;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Basic(optional = false)
    @Column(name = "actividad", nullable = false, length = 32)
    private String actividad;
    @Basic(optional = false)
    @Column(name = "creador", nullable = false, length = 64)
    private String creador;

    public XOrganizacion() {
    }

    public XOrganizacion(Organizacion organizacion, String actividad, String creador) {
        this.organizacionId = organizacion.getId();
        this.codigo = organizacion.getCodigo();
        this.nombre = organizacion.getNombre();
        this.nombreCompleto = organizacion.getNombreCompleto();
        this.rfc = organizacion.getRfc();
        this.curp = organizacion.getCurp();
        Date date = new Date();
        this.dateCreated = date;
        this.lastUpdated = date;
        this.actividad = actividad;
        this.creador = creador;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    public long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(long version) {
        this.version = version;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the organizacionId
     */
    public long getOrganizacionId() {
        return organizacionId;
    }

    /**
     * @param organizacionId the organizacionId to set
     */
    public void setOrganizacionId(long organizacionId) {
        this.organizacionId = organizacionId;
    }

    /**
     * @return the actividad
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the creador
     */
    public String getCreador() {
        return creador;
    }

    /**
     * @param creador the creador to set
     */
    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XOrganizacion)) {
            return false;
        }
        XOrganizacion other = (XOrganizacion) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.um.mateo.modelo.XOrganizacion[id=" + getId() + "]";
    }

}
