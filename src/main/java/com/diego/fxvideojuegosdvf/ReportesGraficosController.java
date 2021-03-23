/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.fxvideojuegosdvf;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import layout.ControladorConNavegabilidad;

public class ReportesGraficosController extends ControladorConNavegabilidad implements Initializable {

    @FXML
    private Button btnVideojuegos;
    
    @FXML
    private Button btnRefresh;
    
    @FXML
     private PieChart chart;

    VideojuegosDao videojuegosDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            videojuegosDao = new VideojuegosDao();
            cargarDatosEnElChart();
        } catch (SQLException ex) {
            Logger.getLogger(ReportesGraficosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void mostrarPantalla(ActionEvent event) {
        this.layout.mostrarComoPantallaActual("videojuegos");
    }

    @FXML
    private void cargarDatosEnElChart() {
        Map<String, Integer> juegosPegi = videojuegosDao.contarJuegosPorPegi();
        
        ObservableList<PieChart.Data> datosParaElChart = FXCollections.observableArrayList();
        
        juegosPegi.forEach((nombreVideojuego, juegosPorPegi)-> {
            
                PieChart.Data data = new PieChart.Data(nombreVideojuego, juegosPorPegi);
                datosParaElChart.add(data);
            
        });
        chart.setData(datosParaElChart);
    }
    
}
