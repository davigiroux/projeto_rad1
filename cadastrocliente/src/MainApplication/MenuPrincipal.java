/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApplication;

import Model.Pedido;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.PedidoController;

/**
 *
 * @author rf3020
 */
public class MenuPrincipal extends Application {

    @FXML
    public void aoClicarBtPedido() throws Exception{
        PedidoController novaTela = new PedidoController();
        novaTela.start(new Stage());
        
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
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
    public static void main(String[] args) {
        launch(args);
    }

}
