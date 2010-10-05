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
import mx.edu.um.mateo.general.modelo.Usuario;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "xusuarios")
public class XUsuario implements Serializable {
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
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    @Basic(optional = false)
    @Column(name = "clave", nullable = false, length = 64)
    private String clave;
    @Basic(optional = false)
    @Column(name = "llave", nullable = false, length = 128)
    private String llave;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido", nullable = false, length = 64)
    private String apellido;
    @Column(name = "correo", length = 128)
    private String correo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;
    @Basic(optional = false)
    @Column(name = "actividad", nullable = false, length = 64)
    private String actividad;
    @Basic(optional = false)
    @Column(name = "creador", nullable = false, length = 64)
    private String creador;

    public XUsuario() {
    }

    public XUsuario(Usuario usuario, String actividad, String creador) {
        this.usuarioId = usuario.getId();
        this.clave = usuario.getClave();
        this.llave = usuario.getLlave();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.correo = usuario.getCorreo();
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
     * @return the usuarioId
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the llave
     */
    public String getLlave() {
        return llave;
    }

    /**
     * @param llave the llave to set
     */
    public void setLlave(String llave) {
        this.llave = llave;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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
        if (!(object instanceof XUsuario)) {
            return false;
        }
        XUsuario other = (XUsuario) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.um.mateo.modelo.XUsuario[id=" + getId() + "]";
    }

}
