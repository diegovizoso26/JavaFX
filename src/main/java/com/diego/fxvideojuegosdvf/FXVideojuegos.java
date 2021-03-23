/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.fxvideojuegosdvf;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import layout.LayoutPane;

public class FXVideojuegos extends Application{

    @Override
    public void start(Stage stage) throws Exception {
         
         LayoutPane layoutPane = new LayoutPane();
        layoutPane.cargarPantalla("videojuegos", ControladorFormularioVideojuegos.class.getResource("FormularioVideojuegos.fxml"));
        layoutPane.cargarPantalla("reportes", ReportesGraficosController.class.getResource("reportesGraficos.fxml"));
        layoutPane.cargarPantalla("login", LoginController.class.getResource("login.fxml"));
        
        layoutPane.mostrarComoPantallaActual("login");
         
        Scene escena = new Scene(layoutPane, 800 , 550);
        escena.getStylesheets().addAll(getClass().getResource("estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/games_24302.png"));
        stage.show();
        
    }
    
    public static void main(String[]  args){
        launch(args);
    }

}
