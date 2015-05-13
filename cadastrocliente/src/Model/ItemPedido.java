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
public class ItemPedido {
    private Integer id_item_pedido;
    private Integer quantidade;
    private double valor_item;
    private Integer id_produto;
    private Integer id; // id do pedido

    public Integer getId_item_pedido() {
        return id_item_pedido;
    }

    public void setId_item_pedido(Integer id_item_pedido) {
        this.id_item_pedido = id_item_pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_item() {
        return valor_item;
    }

    public void setValor_item(double valor_item) {
        this.valor_item = valor_item;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
