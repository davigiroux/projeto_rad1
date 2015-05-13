/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ProdutoDAO;
import Model.Produto;
import Service.Exception.ServiceException;
import java.util.List;

/**
 *
 * @author Davi
 */
public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService() {
        produtoDAO = new ProdutoDAO();
    }

    public void salvar(Produto produto) throws ServiceException {

        if (produto.getDescricao().isEmpty()) {
            throw new ServiceException("Campo Descrição de Produto é obrigatório!");
        }
        
        produtoDAO.salvar(produto);
       
        

    }

    public void excluir(Integer id) {
        produtoDAO.excluir(id);
    }

    public Produto buscarPorId(Integer id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> buscarTodos() {
            return produtoDAO.buscarTodos();
    }
}
