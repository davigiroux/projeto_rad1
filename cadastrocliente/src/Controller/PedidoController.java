/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import DAO.ItemPedidoDAO;
import DAO.ProdutoDAO;
import DAO.TipoEventoDAO;
import Model.Cliente;
import Model.ItemPedido;
import Model.Pedido;
import Model.Produto;
import Model.TipoEvento;
import Service.Exception.ServiceException;
import Service.PedidoService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author rf3020
 */
public class PedidoController implements Initializable {

    private final ClienteDAO cli = new ClienteDAO();
    private final TipoEventoDAO tipo = new TipoEventoDAO();
    private final ProdutoDAO prod = new ProdutoDAO();
    private final ItemPedidoDAO item_pedido = new ItemPedidoDAO();

    @FXML
    private TextField localContratoTF;

    @FXML
    private TextArea observacaoTf;

    @FXML
    private TextField cerimonialTF;

    @FXML
    private ComboBox<Cliente> clienteCB;

    @FXML
    private DatePicker dataEventoDP;

    @FXML
    private DatePicker dataPedidoDP;

    @FXML
    private ComboBox<TipoEvento> tipoEventoCB;

    @FXML
    private TextField horaTF;

    @FXML
    private Button salvarBT;

    @FXML
    private Button limparBT;

    @FXML
    private Label salvoLb;

    @FXML
    private ComboBox<Produto> produtoCb;

    @FXML
    private final TextField valorUTf = new TextField();

    @FXML
    private TableView viewTabela;

    @FXML
    private TableColumn colunaProd;

    @FXML
    private TableColumn colunaQtd;

    @FXML
    private TableColumn colunaValor;

    private final Label valorTotal = new Label();

    @FXML
    TextField qtdTf = new TextField();

    PedidoService pedService = new PedidoService();

    @FXML
    private void aoClicarBtSalvar(ActionEvent event) {
        Pedido pedido = new Pedido();
        pedido.setData_evento(dataEventoDP.getValue().toString());
        pedido.setLocal_evento(localContratoTF.getText());
        pedido.setData_pedido(dataPedidoDP.getValue().toString());
        pedido.setHora_evento(horaTF.getText());
        pedido.setCerimonial(cerimonialTF.getText());
        pedido.setId_cliente(clienteCB.getValue().getId());
        pedido.setId_tipo_evento(tipoEventoCB.getValue().getId_tipo_evento());

        try {
            pedService.salvar(pedido);
            //Mensagem
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("O pedido foi salvo com sucesso! \n Adicione itens ao seu pedido!");

            alert.showAndWait();
        } catch (ServiceException ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void aoClicarBtAdd() {
        ItemPedido item_ped = new ItemPedido();

        item_ped.setId_produto(produtoCb.getValue().getId_produto());
        item_ped.setQuantidade(Integer.parseInt(qtdTf.getText()));
        item_ped.setValor_item(Double.parseDouble(valorUTf.getText())* item_ped.getQuantidade());
        System.out.println(produtoCb.getValue().getValor());
        System.out.println(item_ped.getQuantidade());
        System.out.println(item_ped.getValor_item());
        List<ItemPedido> listaItem = item_pedido.buscarTodos();
        listaItem.add(item_ped);

        colunaProd.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor_item"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        ObservableList<ItemPedido> dados = FXCollections.observableArrayList(listaItem);

        viewTabela.setItems(dados);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Cliente> lista = FXCollections.observableArrayList(cli.buscarTodos());
        this.clienteCB.setItems(lista);

        ObservableList<TipoEvento> lista2 = FXCollections.observableArrayList(tipo.buscarTodos());
        this.tipoEventoCB.setItems(lista2);

        ObservableList<Produto> lista3 = FXCollections.observableArrayList(prod.buscarTodos());
        this.produtoCb.setItems(lista3);
        viewTabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        valorUTf.textProperty().bind(Bindings.selectString(produtoCb.getSelectionModel().selectedItemProperty(), "valor"));

        qtdTf.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                DoubleProperty x = new SimpleDoubleProperty(Double.parseDouble(valorUTf.getText()));
                DoubleProperty y = new SimpleDoubleProperty();

                if (qtdTf.getText().isEmpty()) {
                    valorTotal.setText("0.00");
                } else {
                    y.bind(x.multiply(Double.parseDouble(qtdTf.getText())));
                    valorTotal.setText(y.getValue().toString());
                }
                
                

            }

        });

        produtoCb.valueProperty().addListener(new ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> ov, Produto t, Produto t1) {
                qtdTf.setText("0");
            }
        });

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
}
