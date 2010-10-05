package mx.edu.um.mateo.general.modelo;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "organizaciones", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"}),
    @UniqueConstraint(columnNames = {"codigo"})})
@NamedQueries({
    @NamedQuery(name = "Organizacion.buscaTodos", query = "SELECT o FROM Organizacion o"),
    @NamedQuery(name = "Organizacion.buscaPorCodigo", query = "SELECT o FROM Organizacion o WHERE o.codigo = :codigo"),
    @NamedQuery(name = "Organizacion.buscaPorNombre", query = "SELECT o FROM Organizacion o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Organizacion.buscaPorRfc", query = "SELECT o FROM Organizacion o WHERE o.rfc = :rfc")})
public class Organizacion implements Serializable {
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
    @Size(min=12,max=13)
    private String rfc;
    @Column(name = "curp", length = 18)
    private String curp;
    @JoinTable(name = "organizaciones_usuarios", joinColumns = {
        @JoinColumn(name = "organizacion_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Usuario> usuarios;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="organizacion")
    private List<Empresa> empresas;

    public Organizacion() {
    }

    public Organizacion(Long id) {
        this.id = id;
    }

    public Organizacion(String codigo, String nombre, String nombreCompleto, String rfc, String curp) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.curp = curp;
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

    /**
     * @return the empresas
     */
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * @param empresas the empresas to set
     */
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
    /**
     * Asigna empresa a esta organizacion
     * @param empresa
     */
    public void addToEmpresas(Empresa empresa) {
        this.empresas.add(empresa);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Organizacion)) {
            return false;
        }
        Organizacion other = (Organizacion) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.um.mateo.modelo.Organizacion[id=" + getId() + "]";
    }

}
