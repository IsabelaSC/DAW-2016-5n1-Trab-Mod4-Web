package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Isabela
 */
@ManagedBean(name = "controleLivraria")
@SessionScoped
public class ControleLivraria implements Serializable {

    private LivrariaDAO<Livraria> dao;
    private Livraria objeto;
    private Catalogo catalogo;
    private Boolean novoCatalogo;

    public ControleLivraria() {
        dao = new LivrariaDAO<>();
    }

    public void novoCatalogo() {
        catalogo = new Catalogo();
        novoCatalogo = true;
    }

    public void alterarCatalogo(int index) {
        catalogo = objeto.getCatalogos().get(index);
        novoCatalogo = false;
    }

    public void salvarCatalogo() {
        if (novoCatalogo) {
            objeto.adicionarCatalogo(catalogo);
        }
        UtilMensagens.mensagemInformacao("Catalogo adicionado com sucesso");
    }

    public void removerCatalogo(int index) {
        objeto.removerCatalogo(index);
        UtilMensagens.mensagemInformacao("Catalogo removido com sucesso");
    }

    public String listar() {
        return "/privado/livraria/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Livraria();
        return "formulario";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            UtilMensagens.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }

    public String cancelar() {
        return "listar";
    }

    public String editar(Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar";
        }
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
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

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Boolean getNovoCatalogo() {
        return novoCatalogo;
    }

    public void setNovoCatalogo(Boolean novoCatalogo) {
        this.novoCatalogo = novoCatalogo;
    }

}
