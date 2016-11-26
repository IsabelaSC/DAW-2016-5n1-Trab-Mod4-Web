
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *Isabela
 */
@ManagedBean(name = "controleCatalogo")
@ViewScoped
public class ControleCatalogo implements Serializable {

    private CatalogoDAO<Catalogo> dao;
    private Catalogo objeto;
    private LivrariaDAO daoLivraria;
    
    public ControleCatalogo(){
        dao = new CatalogoDAO<>();
        daoLivraria = new LivrariaDAO();
    }
    
//    public void imprimirRelatorio(){
//        HashMap parametros = new HashMap();
//        UtilRelatorios.imprimeRelatorio("relatorioCatalogosJavaBeans", 
//                parametros, dao.getListaTodos());
//    }
    
    public String listar(){
        return "/privado/catalogo/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Catalogo();        
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            UtilMensagens.mensagemInformacao(dao.getMensagem());            
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());            
        }        
    }   
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            UtilMensagens.mensagemInformacao(dao.getMensagem());
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
        }
    }

    public CatalogoDAO getDao() {
        return dao;
    }

    public void setDao(CatalogoDAO dao) {
        this.dao = dao;
    }

    public Catalogo getObjeto() {
        return objeto;
    }

    public void setObjeto(Catalogo objeto) {
        this.objeto = objeto;
    }

    public LivrariaDAO getDaoLivraria() {
        return daoLivraria;
    }

    public void setDaoLivraria(LivrariaDAO daoLivraria) {
        this.daoLivraria = daoLivraria;
    }
    
}
