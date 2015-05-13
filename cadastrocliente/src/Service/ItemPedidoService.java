/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ItemPedidoDAO;
import DAO.ProdutoDAO;
import Model.ItemPedido;
import Model.Produto;
import Service.Exception.ServiceException;
import java.util.List;

/**
 *
 * @author Davi
 */
public class ItemPedidoService {

    private final ItemPedidoDAO itemDAO;

    public ItemPedidoService() {
        itemDAO = new ItemPedidoDAO();
    }

    public void salvar(ItemPedido item_ped) throws ServiceException {
        itemDAO.salvar(item_ped);
    }

    public void excluir(Integer id) {
        itemDAO.excluir(id);
    }

    public ItemPedido buscarPorId(Integer id) {
        return itemDAO.buscarPorId(id);
    }

    public List<ItemPedido> buscarTodos() {
            return itemDAO.buscarTodos();
    }
}

