/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Davi
 */
import Model.Cliente;
import DAO.ClienteDAO;
import DAO.PedidoDAO;
import Model.Pedido;
import Service.Exception.ServiceException;
import java.util.List;

/**
 *
 * @author rf3020
 */
public class PedidoService {

    private PedidoDAO pedidoDAO;

    public PedidoService() {
        pedidoDAO = new PedidoDAO();
    }

    public void salvar(Pedido pedido) throws ServiceException {

        if (pedido.getData_evento().isEmpty()) {
            throw new ServiceException("Campo de Data Evento é obrigatório!");
        }

        if (pedido.getData_pedido().isEmpty()) {
            throw new ServiceException("Campo Data Pedido é obrigatório!");
        }
        if (pedido.getLocal_evento().isEmpty()) {
            throw new ServiceException("Campo Local Evento é obrigatório!");
        }
        if (pedido.getHora_evento().isEmpty()) {
            throw new ServiceException("Campo Hora Evento é obrigatório!");
        }
        if (pedido.getCerimonial().isEmpty()) {
            throw new ServiceException("Campo Cerimonial é obrigatório!");
        }

        pedidoDAO.salvar(pedido);

    }

    public void excluir(Integer id) {
        pedidoDAO.excluir(id);
    }

    /*public Cliente buscarPorId(Integer id) {
     return pedidoDAO.buscarPorId(id);
     }

     public List<Cliente> buscarTodos() {
     return pedidoDAO.buscarTodos();
     }*/
}
