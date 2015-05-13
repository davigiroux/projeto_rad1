/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import Util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davi
 */

public class ProdutoDAO {
    Connection conexao;
    
    public ProdutoDAO() {
        conexao = ConexaoUtil.getConnection();
    }
    
    public Produto buscarPorId(Integer id) {
        String sql = "select * from produto where idProduto=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            if (resultado.next()) {
                //Instancia de cliente
                Produto pro = new Produto();

                //Atribuindo dados do resultado no objeto produto
                pro.setId_produto(id);
                pro.setDescricao(resultado.getString("descricao"));
                pro.setValor(resultado.getDouble("valor"));
                preparadorSQL.close();
                return pro;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Produto> buscarTodos() {
        String sql = "select * from produto order by id_produto";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (resultado.next()) {
                //Instancia de cliente
                Produto pro = new Produto();

                //Atribuindo dados do resultado no objeto produto
                pro.setId_produto(resultado.getInt("id_produto"));
                pro.setDescricao(resultado.getString("descricao"));
                pro.setValor(resultado.getDouble("valor"));
                //Adicionando produto na lista
                lista.add(pro);
            }
            
            preparadorSQL.close();
            return lista;
        } catch (SQLException ex) {

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void salvar(Produto produto) {
        if (produto.getId_produto()== 0) {
            cadastrar(produto);
        } else {
            alterar(produto);
        }
    }

    public void cadastrar(Produto produto) {
        String sql = "insert  into produto (descricao, valor) values (?,?)";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, produto.getDescricao());
            preparadorSQL.setDouble(2, produto.getValor());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterar(Produto produto) {
        String sql = "update produto set descricao=? ,valor=? where id_produto=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, produto.getDescricao());
            preparadorSQL.setDouble(2, produto.getValor());
            preparadorSQL.setInt(3, produto.getId_produto());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluir(Integer id) {
        String sql = "delete from produto where id_produto=?";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);

            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
