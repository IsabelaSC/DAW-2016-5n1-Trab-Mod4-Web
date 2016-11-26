package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;

/**
 *
 * @author Isabela
 */
public class AutorDAO<T> extends DAOGenerico<Autor> implements Serializable {

   public AutorDAO(){
       super();
       super.setClassePersistente(Autor.class);
   }
}
