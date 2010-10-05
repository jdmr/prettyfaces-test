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
import mx.edu.um.mateo.general.modelo.Empresa;
import mx.edu.um.mateo.general.modelo.Organizacion;
import mx.edu.um.mateo.general.modelo.Rol;
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
        try {
            utx.begin();
            log.debug("Validando los roles");
            Rol rolAdmin;
            Query query = entityManager.createNamedQuery("Rol.buscaTodos");
            List roles = query.getResultList();
            if (roles == null || roles.isEmpty() || roles.size() != 4) {
                log.debug("Creando los roles");
                rolAdmin = new Rol("ROLE_ADMIN");
                Rol rolOrg = new Rol("ROLE_ORG");
                Rol rolEmp = new Rol("ROLE_EMP");
                Rol rolUser = new Rol("ROLE_USER");
                entityManager.persist(rolAdmin);
                entityManager.persist(rolOrg);
                entityManager.persist(rolEmp);
                entityManager.persist(rolUser);
            } else {
                query = entityManager.createNamedQuery("Rol.buscaPorAutoridad");
                query.setParameter("autoridad", "ROLE_ADMIN");
                rolAdmin = (Rol) query.getSingleResult();
            }

            log.debug("Validando los usuarios");
            query = entityManager.createNamedQuery("Usuario.buscaPorRol");
            query.setParameter("rol", rolAdmin);


            log.debug("Validando las organizaciones");
            query = entityManager.createNamedQuery("Organizacion.buscaTodos");
            query.setMaxResults(1);
            List organizaciones =  query.getResultList();
            if (organizaciones == null || organizaciones.isEmpty()) {
                Organizacion organizacion = new Organizacion("UM","UM","Universidad de Montemorelos, A.C.","UMO8409105C4",null);
                Empresa empresa = new Empresa("EMP", "EMPRESA", "EMPRESA", "UMO8409105C4", null, organizacion);
                entityManager.persist(empresa);
            }
            utx.commit();
        } catch(Exception e) {
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
