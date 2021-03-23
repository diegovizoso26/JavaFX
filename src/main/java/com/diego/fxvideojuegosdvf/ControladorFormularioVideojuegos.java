/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.fxvideojuegosdvf;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import layout.ControladorConNavegabilidad;

/**
 * 
 * 
 */
public class ControladorFormularioVideojuegos extends ControladorConNavegabilidad implements Initializable{
    
    @FXML
    TextField nombre ;
    
    @FXML
    TextArea descripcion;
    
    @FXML
    CheckBox PS4;
    
    @FXML
    CheckBox PC;
    
    @FXML
    CheckBox XBox;
    
    @FXML
    CheckBox NintendoSwitch;
    
    @FXML
    TableView<Videojuegos> tablaVideojuegos;
    
    VideojuegosDao videojuegosDao;
    
    @FXML
     ComboBox<String> pegi;
    
    @FXML
     DatePicker fechaLanzamiento;
    
    @FXML
     TextField url;
    
    @FXML
     ImageView portadaJuego;
    
    @FXML
    private Button reportes;
    
    int id=0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            videojuegosDao=new VideojuegosDao();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorFormularioVideojuegos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarVideojuegosDeLaBase();
        configurarTamanhoColumnas();
    }

    private void cargarVideojuegosDeLaBase() {
        ObservableList<Videojuegos> videojuegos=FXCollections.observableArrayList();
        List<Videojuegos> videojuegosEncontrados=videojuegosDao.buscarTodosLosVideojuegos();
        videojuegos.addAll(videojuegosEncontrados);
        tablaVideojuegos.setItems(videojuegos);
       
        
    }
    
    @FXML
    public void guardar() throws SQLException{
       
       Videojuegos videojuegos=new Videojuegos();
       videojuegos.setId(id);
       videojuegos.setNombre(nombre.getText());
       videojuegos.setDescripcion(descripcion.getText());    
       videojuegos.setPegi(pegi.getSelectionModel().getSelectedItem());
       videojuegos.setPS4(PS4.isSelected());
       videojuegos.setPC(PC.isSelected());
       videojuegos.setXBox(XBox.isSelected());
       videojuegos.setNintendoSwitch(NintendoSwitch.isSelected());
       videojuegos.setFechaLanzamiento(fechaLanzamiento.getValue());
       videojuegos.setUrl(url.getText());
       videojuegosDao.guardarOActualizar(videojuegos);
       id=0;
       
        System.out.println("Se ha guardado satisfactoriamente");
       
       cargarVideojuegosDeLaBase();
       
       nombre.clear();
       descripcion.clear();
       pegi.setValue("Selecciona el PEGI del juego");
       PS4.setSelected(false);
       PC.setSelected(false);
       XBox.setSelected(false);
       NintendoSwitch.setSelected(false);
       url.clear();
      
   } 
    
    @FXML
     public void editar(){
        Videojuegos videojuegos=tablaVideojuegos.getSelectionModel().getSelectedItem();
        nombre.setText(videojuegos.getNombre());
        descripcion.setText(videojuegos.getDescripcion());
        PS4.setSelected(videojuegos.isPS4());
        PC.setSelected(videojuegos.isPC());
        XBox.setSelected(videojuegos.isXBox());
        NintendoSwitch.setSelected(videojuegos.isNintendoSwitch());
        fechaLanzamiento.setValue(videojuegos.getFechaLanzamiento());
        url.setText(videojuegos.getUrl());
        
        Image img = new Image(videojuegos.getUrl());
        portadaJuego.setImage(img);
        portadaJuego.autosize();
        
        id=videojuegos.getId();
    }
    
    @FXML
    public void eliminar(){
        Videojuegos videojuegos=tablaVideojuegos.getSelectionModel().getSelectedItem();
       videojuegosDao.eliminar(videojuegos);
        cargarVideojuegosDeLaBase();
    }

    private void configurarTamanhoColumnas() {
        tablaVideojuegos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      ObservableList<TableColumn<Videojuegos, ?>> columnas=tablaVideojuegos.getColumns();
      columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 3); //3%
      columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 8); //8%
      columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 12); //12%
      columnas.get(3).setMaxWidth(1f* Integer.MAX_VALUE * 5);//5%
      columnas.get(4).setMaxWidth(1f* Integer.MAX_VALUE * 5); //5%
      columnas.get(5).setMaxWidth(1f* Integer.MAX_VALUE * 5); //5%
      columnas.get(6).setMaxWidth(1f* Integer.MAX_VALUE * 5); //5%
      columnas.get(7).setMaxWidth(1f* Integer.MAX_VALUE * 5); //5%
      columnas.get(8).setMaxWidth(1f* Integer.MAX_VALUE * 5); //5%
      
    }

    @FXML
    private void mostrarPantalla(ActionEvent event) {
        this.layout.mostrarComoPantallaActual("reportes");
    }
    
    @FXML
    private void logOut(ActionEvent event) {
        this.layout.mostrarComoPantallaActual("login");
    }

}
