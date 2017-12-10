package br.com.deliciagelada.deliciagelada.Objects;

/**
 * Created by 16254861 on 29/11/2017.
 */

public class Produto {

    private int codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private String foto;
    private Boolean ativo;
    private Double estrelas;
    private int codSubCategoria;

    //Fabrica de Produtos
    public static Produto create(String nome,String descricao, Double preco, String foto, Double estrelas){

        Produto p = new Produto();
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
        p.setEstrelas(estrelas);
        p.setFoto(foto);
        return p;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getCodSubCategoria() {
        return codSubCategoria;
    }

    public void setCodSubCategoria(int codSubCategoria) {
        this.codSubCategoria = codSubCategoria;
    }


    public Double getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }
}
