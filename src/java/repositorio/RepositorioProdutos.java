/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author Irla Silva
 */
public class RepositorioProdutos {

    private static RepositorioProdutos instancia;
    private List<Produto> listaProdutos;
    //variavel que simula o autoincrement
    private int autoIncrement;

    private RepositorioProdutos() {
        listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto(1, "Arroz", 3.90));
        listaProdutos.add(new Produto(2, "Carne", 22.00));
        listaProdutos.add(new Produto(3, "Brócolis", 7.50));
        //inicia com 4 devido aos registros já inseridos
        autoIncrement = 4;
    }

    //método referencia o repositório para um único servidor - Singleton
    //synchronized diretiva que gerencia multiplos acessos
    public static synchronized RepositorioProdutos getInstance() {
        if (instancia == null) {
            instancia = new RepositorioProdutos();
        }
        return instancia;
    }

    public void inserir(Produto p) {
        //se o código não for informado o autoincrement adiciona o valor
        if (p.getCodigo() == 0) {
            p.setCodigo(autoIncrement++);
        }
        listaProdutos.add(p);
    }

    public List<Produto> listar() {
        return listaProdutos;
    }

    public Produto buscarPorCodigo(int codigo) {
        for (Produto p : listaProdutos) {
            if (p.getCodigo() == codigo) {
                //clonando o objeto para leitura
                return new Produto(p.getCodigo(), p.getNome(), p.getPreco());
            }
        }
        return null;
    }

    //método auxiliar dos métodos atualizar e excluir
    private int getIndice(int codigo) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public void atualizar(Produto p) {
        listaProdutos.set(this.getIndice(p.getCodigo()), p);
    }

    public void excluir(Produto p) {
        listaProdutos.set(this.getIndice(p.getCodigo()), p);
    }
}
