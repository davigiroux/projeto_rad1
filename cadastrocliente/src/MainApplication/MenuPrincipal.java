/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApplication;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rf3020
 */
public class MenuPrincipal extends Application {

    private Stage telaPrincipal = new Stage();
    private Parent root;

    @FXML
    public void aoClicarBtPedido(ActionEvent event) throws Exception {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Pedido.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Stage getTelaPrincipal() {
        return telaPrincipal;
    }

    public void setTelaPrincipal(Stage telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    @Override
    public void start(Stage stage) throws Exception {
        setTelaPrincipal(stage);
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Scene scene = new Scene(root);
        telaPrincipal.setScene(scene);

        telaPrincipal.show();

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
