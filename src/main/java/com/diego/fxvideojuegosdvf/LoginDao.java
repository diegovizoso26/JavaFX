package com.diego.fxvideojuegosdvf;

import static com.diego.fxvideojuegosdvf.VideojuegosDao.conexion;
import static com.diego.fxvideojuegosdvf.VideojuegosDao.usuario;
import static com.diego.fxvideojuegosdvf.VideojuegosDao.contrasena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {

    public LoginDao() {
        crearTablaSiNoExiste();
        insertarUsuarioSiNoHay();
    }

    private void crearTablaSiNoExiste() {
        try ( Connection conexionDB = DriverManager.getConnection(conexion, usuario, contrasena)) {
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuarios"
                    + "(id INTEGER auto_increment, "
                    + "usuario VARCHAR(255), "
                    + "contrasena VARCHAR(255))";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarUsuarioSiNoHay() {
        boolean existeUsuario = existeUsuario("admin", "1234");
        if (!existeUsuario) {
            try ( Connection conexionDB = DriverManager.getConnection(conexion, usuario, contrasena)) {
                Statement statement = conexionDB.createStatement();
                String sql = "INSERT INTO usuarios(usuario, contrasena) VALUES('admin', '1234')";
                statement.executeUpdate(sql);
            } catch (Exception e) {
                throw new RuntimeException("Ocurrió un errror al guardar la información: " + e.getMessage());
            }
        }
    }

    public boolean existeUsuario(String username, String password) {
        try ( Connection conexionDB = DriverManager.getConnection(conexion, usuario, contrasena)) {
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT * FROM usuarios WHERE usuario='" + username
                    + "' AND contrasena='" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al consultar la información: " + e.getMessage());
        }
    }
}
