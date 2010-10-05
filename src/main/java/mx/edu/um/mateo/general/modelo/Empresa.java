package mx.edu.um.mateo.general.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "empresas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"organizacion_id", "nombre"}),
    @UniqueConstraint(columnNames = {"organizacion_id", "codigo"})})
@NamedQueries({
    @NamedQuery(name = "Empresa.buscaTodos", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.buscaPorCodigo", query = "SELECT e FROM Empresa e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Empresa.buscaPorNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresa.buscaPorRfc", query = "SELECT e FROM Empresa e WHERE e.rfc = :rfc")})
public class Empresa implements Serializable {
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
    @ManyToOne(optional = false)
    private Organizacion organizacion;
    @JoinTable(name = "empresas_usuarios", joinColumns = {
        @JoinColumn(name = "empresa_usuarios_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public Empresa() {
    }

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(String codigo, String nombre, String nombreCompleto, String rfc, String curp, Organizacion organizacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.curp = curp;
        this.organizacion = organizacion;
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
     * @return the organizacion
     */
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    /**
     * @param organizacion the organizacion to set
     */
    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
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
    
    public void addToUsuarios(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.um.mateo.modelo.Empresa[id=" + getId() + "]";
    }

}
