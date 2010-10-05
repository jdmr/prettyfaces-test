package mx.edu.um.mateo.bootstrap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import mx.edu.um.mateo.auditable.general.XEmpresa;
import mx.edu.um.mateo.auditable.general.XOrganizacion;
import mx.edu.um.mateo.auditable.general.XUsuario;
import mx.edu.um.mateo.general.modelo.Empresa;
import mx.edu.um.mateo.general.modelo.Organizacion;
import mx.edu.um.mateo.general.modelo.Rol;
import mx.edu.um.mateo.general.modelo.Usuario;
import mx.edu.um.mateo.general.util.Constantes;
import mx.edu.um.mateo.general.util.UsuarioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jdmr
 */
@Singleton
@Startup
public class InicializaAplicacion {

    private static Logger log = LoggerFactory.getLogger(InicializaAplicacion.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private UserTransaction utx;

    public InicializaAplicacion() {
    }

    @PostConstruct
    public void init() {
        log.info("Inicializando Aplicacion");
        Query query;
        Organizacion organizacion = null;
        Empresa empresa = null;
        try {
            utx.begin();
            log.debug("Validando las organizaciones");
            query = entityManager.createNamedQuery("Organizacion.buscaTodos");
            query.setMaxResults(1);
            List organizaciones =  query.getResultList();
            if (organizaciones == null || organizaciones.isEmpty()) {
                organizacion = new Organizacion("UM","UM","Universidad de Montemorelos, A.C.","UMO8409105C4",null);
                empresa = new Empresa("EMP", "EMPRESA", "EMPRESA", "UMO8409105C4", null, organizacion);
                organizacion.addToEmpresas(empresa);
                entityManager.persist(organizacion);
                // Historial
                XEmpresa xempresa = new XEmpresa(empresa, Constantes.CREAR, "admin");
                entityManager.persist(xempresa);
                XOrganizacion xorganizacion = new XOrganizacion(organizacion, Constantes.CREAR, "admin");
                entityManager.persist(xorganizacion);
            } else {
                organizacion = (Organizacion)organizaciones.get(0);
                empresa = organizacion.getEmpresas().get(0);
            }
            
            log.debug("Validando los roles");
            Rol rolAdmin;
            query = entityManager.createNamedQuery("Rol.buscaTodos");
            List roles = query.getResultList();
            if (roles == null || roles.isEmpty()) {
                log.debug("Creando los roles");
                rolAdmin = new Rol("ROLE_ADMIN");
                Rol rolOrg = new Rol("ROLE_ORG");
                Rol rolEmp = new Rol("ROLE_EMP");
                Rol rolUser = new Rol("ROLE_USER");
                entityManager.persist(rolAdmin);
                entityManager.persist(rolOrg);
                entityManager.persist(rolEmp);
                entityManager.persist(rolUser);
                roles.clear();
                roles.add(rolAdmin);
                roles.add(rolOrg);
                roles.add(rolEmp);
                roles.add(rolUser);
            } else {
                query = entityManager.createNamedQuery("Rol.buscaPorAutoridad");
                query.setParameter("autoridad", "ROLE_ADMIN");
                rolAdmin = (Rol) query.getSingleResult();
            }

            log.debug("Validando los usuarios");
            query = entityManager.createNamedQuery("Usuario.buscaPorRol");
            query.setParameter("rol", rolAdmin);
            List usuarios = query.getResultList();
            if (usuarios == null || usuarios.isEmpty()) {
                log.debug("Creando usuario administrador");
                Usuario usuario = new Usuario("admin", UsuarioUtil.encriptaLlave("admin"), "David", "Mendoza", "david.mendoza@um.edu.mx", roles, empresa);
                entityManager.persist(usuario);
                entityManager.persist(new XUsuario(usuario,Constantes.CREAR,"admin"));
                
                log.debug("Agregando usuario a la empresa");
                empresa.addToUsuarios(usuario);
                //entityManager.persist(empresa);
                log.debug("Agregando usuario a la organizacion");
                organizacion.addToUsuarios(usuario);
                entityManager.persist(organizacion);
            }

            utx.commit();
        } catch(Exception e) {
            log.error("Hubo un problema al iniciar la aplicacion",e);
            try {
                utx.rollback();
            } catch (Exception ex) {
                log.error("No se pudo hacer rollback",ex);
            }
        }

        log.info("Aplicacion inicializada");
    }

    @PreDestroy
    public void termina() {
        log.info("Dando de baja aplicacion");
    }
}
