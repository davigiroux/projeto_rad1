/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Pedido;
import Service.Exception.ServiceException;
import Service.PedidoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author rf3020
 */
public class PedidoController extends Application{

    @FXML
    private TextField localContratoTF;

    @FXML
    private TextField cerimonialTF;

    @FXML
    private ComboBox clienteCB;

    @FXML
    private DatePicker dataEventoDP;

    @FXML
    private DatePicker dataPedidoDP;

    @FXML
    private ComboBox tipoEventoCB;

    @FXML
    private TextField horaTF;

    @FXML
    private Button salvarBT;

    @FXML
    private Button limparBT;

    @FXML
    private Label salvoLb;

    PedidoService pedService = new PedidoService();

    @FXML
    private void aoClicarBtSalvar(ActionEvent event) {
        Pedido pedido = new Pedido();
        pedido.setData_evento(dataEventoDP.getValue().toString());
        pedido.setLocal_evento(localContratoTF.getText());
        pedido.setData_pedido(dataPedidoDP.getValue().toString());
        pedido.setHora_evento(horaTF.getText());
        pedido.setCerimonial(cerimonialTF.getText());

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
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Pedido.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

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
