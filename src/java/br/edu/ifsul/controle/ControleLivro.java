package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Isabela
 */
@ManagedBean(name = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable {

    private LivroDAO<Livro> dao;
    private Livro objeto;
    private IdiomaDAO<Idioma> daoIdioma;
    private FormatoDAO<Formato> daoFormato;
    private CatalogoDAO<Catalogo> daoCatalogo;
    private AutorDAO daoAutor;
    private Autor  autor;
    
    public ControleLivro() {
        dao = new LivroDAO<>();
        daoIdioma = new IdiomaDAO<>();
        daoFormato = new FormatoDAO<>();
        daoCatalogo = new CatalogoDAO<>();
        daoAutor = new AutorDAO();
    }

        public void adicionarAutoria(){
        if(autor != null){
            if(!objeto.getAutorias().contains(autor)){
                objeto.getAutorias().add(autor);
                UtilMensagens.mensagemInformacao("Autor informado com sucesso");
            }else{
                UtilMensagens.mensagemInformacao("Autor já adicionado na lista");
            }
        }
    }
    
    public void removerAutoria(int index){
        autor = objeto.getAutorias().get(index);
        objeto.getAutorias().remove(autor);
        UtilMensagens.mensagemInformacao("Autor removido com sucesso");
    }
    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getIsbn() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            UtilMensagens.mensagemInformacao(dao.getMensagem());
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            UtilMensagens.mensagemErro(e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.localizar(id);
            if (dao.remover(objeto)) {
                UtilMensagens.mensagemInformacao(dao.getMensagem());
            } else {
                UtilMensagens.mensagemErro(dao.getMensagem());
            }
        } catch (Exception e) {
            UtilMensagens.mensagemErro(e.getMessage());
        }
    }

    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
    }

    public CatalogoDAO<Catalogo> getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO<Catalogo> daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public AutorDAO getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO daoAutor) {
        this.daoAutor = daoAutor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

  
}
