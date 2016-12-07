package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroDAO;
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
    
    
//    private Telefone telefone;
    private Boolean novoTelefone;
//    private ProdutoDAO<Produto> daoProduto;
//    private Produto produto;

    public ControleLivro() {
        dao = new LivroDAO<>();
        daoIdioma = new IdiomaDAO<>();
        daoFormato = new FormatoDAO<>();
        
        
//        daoProduto = new ProdutoDAO<>(); //n para n
    }
    
    //n para n
//    public void adicionarDesejo(){
//        if (produto != null){
//            if(!objeto.getDesejos().contains(produto)){
//                objeto.getDesejos().add(produto);
//                Util.mensagemInformacao("Desejo adicionado com sucesso");
//            } else {
//                Util.mensagemErro("Produto já existe na lista");
//            } 
//        }
//    }
    
//    public void removerDesejo(int index){
//        produto = objeto.getDesejos().get(index);
//        objeto.getDesejos().remove(produto);
//        Util.mensagemInformacao("Desejo removido com sucesso!");
//    }

//    public void novoTelefone(){
//        telefone = new Telefone();
//        novoTelefone = true;
//    }
//    
//    public void alterarTelefone(int index){
//        telefone = objeto.getTelefones().get(index);
//        novoTelefone = false;
//    }
    
//    public void salvarTelefone(){
//        if (novoTelefone){
//            objeto.adicionarTelefone(telefone);
//        }
//        Util.mensagemInformacao("Telefone adicionado com sucesso");
//    }
    
//    public void removerTelefone(int index){
//        objeto.removerTelefone(index);
//        Util.mensagemInformacao("Telefone removido com sucesso");
//    }
    
    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getIsbn()== null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }        
        if(persistiu){
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
    
    public void remover(Integer id){
        try {
            objeto = dao.localizar(id);
            if(dao.remover(objeto)){
                UtilMensagens.mensagemInformacao(dao.getMensagem());
            } else {
                UtilMensagens.mensagemErro(dao.getMensagem());
            }  
        } catch(Exception e){
            UtilMensagens.mensagemErro(e.getMessage());
        }
    }

    public LivroDAO getDao() {
        return dao;
    }

    public void setDao(LivroDAO dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public IdiomaDAO getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

//    public Telefone getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(Telefone telefone) {
//        this.telefone = telefone;
//    }
//
//    public Boolean getNovoTelefone() {
//        return novoTelefone;
//    }
//
//    public void setNovoTelefone(Boolean novoTelefone) {
//        this.novoTelefone = novoTelefone;
//    }
//
//    public ProdutoDAO getDaoProduto() {
//        return daoProduto;
//    }
//
//    public void setDaoProduto(ProdutoDAO daoProduto) {
//        this.daoProduto = daoProduto;
//    }
//
//    public Produto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(Produto produto) {
//        this.produto = produto;
//    }
}
