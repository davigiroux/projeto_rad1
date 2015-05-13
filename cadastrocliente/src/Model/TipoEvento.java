/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Davi
 */
public class TipoEvento {
    private Integer id_tipo_evento;
    private String descricao;

    public Integer getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(Integer id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString(){
        return getDescricao();
    }
}
