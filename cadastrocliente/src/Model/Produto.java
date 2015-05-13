/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Davi
 */
public class Produto {

    IntegerProperty id_produto = new SimpleIntegerProperty();
    StringProperty descricao = new SimpleStringProperty();
    DoubleProperty valor = new SimpleDoubleProperty();

    //Getters e Setters para idProduto
    public final Integer getId_produto() {
        return id_produto.get();
    }

    public final void setId_produto(Integer value) {
        id_produto.set(value);
    }

    public IntegerProperty idProdutoProperty() {
        return id_produto;
    }

    //Getters e Setters para descricao
    public final String getDescricao() {
        return descricao.get();
    }

    public final void setDescricao(String value) {
        descricao.set(value);
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    //Getters e Setters para valor
    public final Double getValor() {
        return valor.get();
    }

    public final void setValor(Double value) {
        valor.set(value);
    }

    public DoubleProperty valorProperty() {
        return valor;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
