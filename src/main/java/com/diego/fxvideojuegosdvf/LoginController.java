/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.fxvideojuegosdvf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import layout.ControladorConNavegabilidad;

public class LoginController extends ControladorConNavegabilidad implements Initializable{

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button btnEntrar;

    LoginDao loginDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginDao = new LoginDao();
    }

    @FXML
    private void mostrarPantalla(ActionEvent event) {
        boolean loginOk = loginDao.existeUsuario(txtUsuario.getText(), txtContrasena.getText());
        if (loginOk) {
            this.layout.mostrarComoPantallaActual("videojuegos");
            txtUsuario.clear();
            txtContrasena.clear();
        }
    }

    
}
