/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ItemPedido;
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
public class ItemPedidoDAO {
 
    Connection conexao;

    public ItemPedidoDAO() {
        conexao = ConexaoUtil.getConnection();
    }

    public ItemPedido buscarPorId(Integer id) {
        String sql = "select * from item_pedido where id_produto=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            if (resultado.next()) {
                //Instancia de cliente
                ItemPedido item_ped = new ItemPedido();

                //Atribuindo dados do resultado no objeto cliente
                item_ped.setId_item_pedido(id);
                item_ped.setQuantidade(resultado.getInt("quantidade"));
                preparadorSQL.close();
                return item_ped;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<ItemPedido> buscarTodos() {
        String sql = "select * from item_pedido order by id_item_pedido";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            List<ItemPedido> lista = new ArrayList<>();
            while (resultado.next()) {
                //Instancia de cliente
                ItemPedido item_ped = new ItemPedido();

                //Atribuindo dados do resultado no objeto cliente
                item_ped.setId_item_pedido(resultado.getInt("id_item_evento"));
                item_ped.setQuantidade(resultado.getInt("quantidade"));
                item_ped.setValor_item(resultado.getDouble("valor_item"));
                item_ped.setId_produto(resultado.getInt("id_produto"));
                item_ped.setId(resultado.getInt("id"));
                //Adicionando cliente na lista
                lista.add(item_ped);
            }
            
            preparadorSQL.close();
            return lista;
        } catch (SQLException ex) {

            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void salvar(ItemPedido item_ped) {
        if (item_ped.getId_item_pedido()== null) {
            cadastrar(item_ped);
        } else {
            alterar(item_ped);
        }
    }

    public void cadastrar(ItemPedido item_ped) {
        String sql = "insert  into item_pedido (quantidade, valor_item, id_produto, id) values (?,?,?,?)";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, item_ped.getQuantidade());
            preparadorSQL.setDouble(2, item_ped.getValor_item());
            preparadorSQL.setInt(3, item_ped.getId_produto());
            preparadorSQL.setInt(4, item_ped.getId());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterar(ItemPedido item_ped) {
        String sql = "update item_pedido set quantidade=?, valor_item=?, id=?, id_produto=? where id=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, item_ped.getQuantidade());
            preparadorSQL.setDouble(2, item_ped.getValor_item());
            preparadorSQL.setInt(3, item_ped.getId());
            preparadorSQL.setInt(4, item_ped.getId_produto());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluir(Integer id) {
        String sql = "delete from item_pedido where id=?";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);

            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
