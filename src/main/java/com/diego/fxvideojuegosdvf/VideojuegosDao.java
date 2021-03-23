/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.fxvideojuegosdvf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class VideojuegosDao {

    public static final String conexion = "jdbc:h2:./Videojuegos";
    public static final String usuario = "root";
    public static final String contrasena = "";

    public VideojuegosDao() throws SQLException {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        try ( Connection conexionBase = DriverManager.getConnection(conexion, usuario, contrasena)) {
            Statement statement = conexionBase.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS videojuegos"
                    + "(id INTEGER auto_increment, "
                    + " nombre VARCHAR(255), "
                    + " descripcion VARCHAR(255), " 
                    + "PEGI VARCHAR(255), "
                    + "PS4 BOOLEAN, "
                    + "PC BOOLEAN, " 
                    + "XBox BOOLEAN, "
                    + "NintendoSwitch BOOLEAN, "
                    + "fechaLanzamiento DATE, "
                    + "url VARCHAR(10000))";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void guardarOActualizar(Videojuegos videojuegos) throws SQLException{
        if(videojuegos.getId()==0){
            guardar(videojuegos);
        }else{
            actualizar(videojuegos);
        }
        
    }
    
     public void guardar(Videojuegos videojuegos) throws SQLException{
         LocalDate locald = videojuegos.getFechaLanzamiento();
        java.sql.Date sqlDate = java.sql.Date.valueOf(locald);
        try(Connection conexionBase=DriverManager.getConnection(conexion, usuario, contrasena)){
            Statement statement=conexionBase.createStatement();
            String sql="INSERT INTO videojuegos(nombre, descripcion, PEGI, PS4, PC, XBox, NintendoSwitch, fechaLanzamiento, url) "
            + " VALUES ('" + videojuegos.getNombre() + "', '" + videojuegos.getDescripcion() + "', '"+ videojuegos.getPegi()   +"'," + videojuegos.isPS4() + " ,"   + videojuegos.isPC() + " ,"   + videojuegos.isXBox() + " ,"   + videojuegos.isNintendoSwitch() + ",'" + sqlDate + "', '" + videojuegos.getUrl() + "' )";
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al guardar el Videojuego: " + e.getMessage());
        }
    }
     
     public void actualizar(Videojuegos videojuegos){
         try(Connection conexionBase=DriverManager.getConnection(conexion, usuario, contrasena)){
            Statement statement=conexionBase.createStatement();
            String sql="UPDATE videojuegos SET nombre=' "
                    +  videojuegos.getNombre() + "', descripcion='" + videojuegos.getDescripcion() + "', PEGI='" + videojuegos.getPegi() + "',  PS4=" + videojuegos.isPS4() + ",  PC=" + videojuegos.isPC() + ",  XBox=" + videojuegos.isXBox() + ", NintendoSwitch=" + videojuegos.isNintendoSwitch() + ", fechaLanzamiento='" + videojuegos.getFechaLanzamiento() + "', url='" + videojuegos.getUrl() + "' WHERE id=" + videojuegos.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al actualizar el Videojuego: " + e.getMessage());
        }
    }
     
      public void eliminar(Videojuegos videojuegos){
        try(Connection conexionBase=DriverManager.getConnection(conexion, usuario, contrasena)){
            Statement statement=conexionBase.createStatement();
            String sql="DELETE FROM videojuegos WHERE id=" + videojuegos.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar el Videojuego: " + e.getMessage());
        }
    }
      
      public List<Videojuegos> buscarTodosLosVideojuegos(){
        List<Videojuegos> videojuegos=new ArrayList<>();
        try(Connection conexionBase=DriverManager.getConnection(conexion, usuario, contrasena)){
            Statement statement=conexionBase.createStatement();
            String sql="SELECT * FROM videojuegos ORDER BY id";
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Videojuegos videojuego=new Videojuegos();
                videojuego.setDescripcion(resultSet.getString("descripcion"));
                videojuego.setPegi(resultSet.getString("pegi"));
                videojuego.setPS4(resultSet.getBoolean("PS4"));
                videojuego.setPC(resultSet.getBoolean("PC"));
                videojuego.setXBox(resultSet.getBoolean("XBox"));
                videojuego.setNintendoSwitch(resultSet.getBoolean("NintendoSwitch"));
                videojuego.setNombre(resultSet.getString("nombre"));
                videojuego.setId(resultSet.getInt("id"));
                videojuego.setFechaLanzamiento(resultSet.getDate("fechaLanzamiento").toLocalDate());
                videojuego.setUrl(resultSet.getString("url"));
                System.out.println(videojuego.toString());
                videojuegos.add(videojuego);
            }
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al consultar la lista de Videojuegos: " + e.getMessage());
        }
        return videojuegos;
    }
      
      public Map<String, Integer> contarJuegosPorPegi(){
          List<Videojuegos> videojuegos = buscarTodosLosVideojuegos();
          Map<String, Integer> juegosPorPegi = new HashMap();
          
          try(Connection conexionDB = DriverManager.getConnection(conexion, usuario, contrasena)){
              Statement statement = conexionDB.createStatement();
              String sql = "SELECT id, count(*) as juegosPorPegi FROM videojuegos GROUP BY PEGI";
              ResultSet resultSet = statement.executeQuery(sql);
              while(resultSet.next()){
                  int idVideojuegos = resultSet.getInt("id");
                  int cantidadJuegosPorPegi = resultSet.getInt("juegosPorPegi");
                  
                  for(Videojuegos videojuego : videojuegos){
                      if(videojuego.getId() == idVideojuegos){
                          juegosPorPegi.put(videojuego.getPegi(), cantidadJuegosPorPegi);
                          break;
                      }
                  }
              }
          }catch(Exception e){
              throw new RuntimeException("Ocurrió un error al consultar la información: " + e.getMessage());
          }
        return juegosPorPegi;
      }
}
