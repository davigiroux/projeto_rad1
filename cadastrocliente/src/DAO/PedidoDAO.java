/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pedido;
import Util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rf3020
 */
public class PedidoDAO {

   Connection conexao;

    public PedidoDAO() {
        conexao = ConexaoUtil.getConnection();
    }
   
   
    
   public void salvar(Pedido pedido) {
       String sql ="insert  into pedido (cerimonial,local_evento, data_pedido, data_evento, hora_evento, id_cliente, observacao, id_tipo_evento) values (?,?,?,?,?,?,?,?)";
       try {
           PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
           preparadorSQL.setString(1, pedido.getCerimonial());
           preparadorSQL.setString(2, pedido.getLocal_evento());
           preparadorSQL.setString(3, pedido.getData_pedido());
           preparadorSQL.setString(4, pedido.getData_evento());
           preparadorSQL.setString(5, pedido.getHora_evento());
           preparadorSQL.setInt(6, pedido.getId_cliente());
           preparadorSQL.setString(7, pedido.getObservacao());
           preparadorSQL.setInt(8, pedido.getId_tipo_evento());
           preparadorSQL.execute();
           preparadorSQL.close();
       } catch (SQLException ex) {
           Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }

    public void excluir(Integer id) {
        String sql = "delete from pedido where id=?";
                
             try {
           PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
           preparadorSQL.setInt(1, id);
         
           preparadorSQL.execute();
           preparadorSQL.close();
       } catch (SQLException ex) {
           Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
