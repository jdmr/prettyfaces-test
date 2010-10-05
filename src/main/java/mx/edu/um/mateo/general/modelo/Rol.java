package mx.edu.um.mateo.general.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "roles", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"autoridad"})})
@NamedQueries({
    @NamedQuery(name = "Rol.buscaTodos", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.buscaPorAutoridad", query = "SELECT r FROM Rol r WHERE r.autoridad = :autoridad")})
public class Rol implements Serializable {
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
    @Column(name = "autoridad", nullable = false, length = 32)
    private String autoridad;
    @ManyToMany(mappedBy="roles")
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public Rol() {
    }

    public Rol(Long id) {
        this.id = id;
    }

    public Rol(Long id, long version, String autoridad) {
        this.id = id;
        this.version = version;
        this.autoridad = autoridad;
    }

    public Rol(String autoridad) {
        this.autoridad = autoridad;
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
     * @return the autoridad
     */
    public String getAutoridad() {
        return autoridad;
    }

    /**
     * @param autoridad the autoridad to set
     */
    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return autoridad;
    }

}
