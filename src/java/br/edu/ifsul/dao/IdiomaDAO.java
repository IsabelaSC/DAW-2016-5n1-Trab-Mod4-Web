
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Idioma;
import java.io.Serializable;

/**
 *
 * @author Isabela
 */
public class IdiomaDAO<T> extends DAOGenerico<Idioma>implements Serializable {

    public IdiomaDAO(){
        super();
        super.setClassePersistente(Idioma.class);
        super.setOrdem("nome");// ordem padrão
    }

}
