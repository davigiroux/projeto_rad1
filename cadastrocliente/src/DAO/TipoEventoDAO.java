/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.TipoEvento;
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
public class TipoEventoDAO {
 
    Connection conexao;

    public TipoEventoDAO() {
        conexao = ConexaoUtil.getConnection();
    }

    public TipoEvento buscarPorId(Integer id) {
        String sql = "select * from tipo_evento where id_tipo_evento=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            if (resultado.next()) {
                //Instancia de cliente
                TipoEvento tip = new TipoEvento();

                //Atribuindo dados do resultado no objeto cliente
                tip.setId_tipo_evento(id);
                tip.setDescricao(resultado.getString("descricaoe"));
                preparadorSQL.close();
                return tip;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<TipoEvento> buscarTodos() {
        String sql = "select * from tipo_evento order by id_tipo_evento";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            List<TipoEvento> lista = new ArrayList<>();
            while (resultado.next()) {
                //Instancia de cliente
                TipoEvento tip = new TipoEvento();

                //Atribuindo dados do resultado no objeto cliente
                tip.setId_tipo_evento(resultado.getInt("id_tipo_evento"));
                tip.setDescricao(resultado.getString("descricao"));
                //Adicionando cliente na lista
                lista.add(tip);
            }
            
            preparadorSQL.close();
            return lista;
        } catch (SQLException ex) {

            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void salvar(TipoEvento tipo) {
        if (tipo.getId_tipo_evento()== null) {
            cadastrar(tipo);
        } else {
            alterar(tipo);
        }
    }

    public void cadastrar(TipoEvento tipo) {
        String sql = "insert  into tipo_evento (descricao) values (?)";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, tipo.getDescricao());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterar(TipoEvento tipo) {
        String sql = "update tipo_evento set descricao=? where id=?";
        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, tipo.getDescricao());
            preparadorSQL.setInt(3, tipo.getId_tipo_evento());
            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluir(Integer id) {
        String sql = "delete from tipo_evento where id=?";

        try {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);

            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
