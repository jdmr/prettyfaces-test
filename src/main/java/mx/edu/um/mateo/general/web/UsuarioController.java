package mx.edu.um.mateo.general.web;

import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.um.mateo.general.modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jdmr
 */
@Named
@Stateful
public class UsuarioController {
    
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public UsuarioController() {}
    
    public List<Usuario> getUsuarios() {
        Query query = entityManager.createNamedQuery("Usuario.buscaTodos");
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }   
    
}
