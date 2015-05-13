/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ClienteDAO;
import DAO.TipoEventoDAO;
import Model.Cliente;
import Model.TipoEvento;
import Service.Exception.ServiceException;
import java.util.List;

/**
 *
 * @author Davi
 */
public class TipoEventoService {

    private final TipoEventoDAO tipoDAO;

    public TipoEventoService() {
        tipoDAO = new TipoEventoDAO();
    }

    public void salvar(TipoEvento tipo) throws ServiceException {

        if (tipo.getDescricao().isEmpty()) {
            throw new ServiceException("Campo Tipo Evento é obrigatório!");
        }

        tipoDAO.salvar(tipo);

    }

    public void excluir(Integer id) {
        tipoDAO.excluir(id);
    }

    public TipoEvento buscarPorId(Integer id) {
        return tipoDAO.buscarPorId(id);
    }

    public List<TipoEvento> buscarTodos() {
            return tipoDAO.buscarTodos();
    }
}
