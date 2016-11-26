package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *Isabela
 */
@ManagedBean(name = "controleLivraria")
@SessionScoped
public class ControleLivraria implements Serializable {

    private LivrariaDAO dao;
    private Livraria objeto;
    
    public ControleLivraria(){
        dao = new LivrariaDAO();
    }
    
    public String listar(){
        return "/privado/livraria/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Livraria();
        return "formulario";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            UtilMensagens.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
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

    public LivrariaDAO getDao() {
        return dao;
    }

    public void setDao(LivrariaDAO dao) {
        this.dao = dao;
    }

    public Livraria getObjeto() {
        return objeto;
    }

    public void setObjeto(Livraria objeto) {
        this.objeto = objeto;
    }
    
}
