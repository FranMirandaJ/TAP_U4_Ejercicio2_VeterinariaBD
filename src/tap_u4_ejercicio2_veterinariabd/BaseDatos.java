package tap_u4_ejercicio2_veterinariabd;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

    Connection conexion;
    Statement transaccion;
    ResultSet cursor;

    String cadenaConexion = "jdbc:mysql://btcv1qobpz98ikw44tz4-mysql.services.clever-cloud.com:3306/btcv1qobpz98ikw44tz4?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "uuo7m853sumtxraa";
    String pass = "6zcUWfXda8RowuVNgFos";

    public BaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
            transaccion = conexion.createStatement();

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    public boolean insertar(Mascota p) {
        String SQL_Insertar = "INSERT INTO `Mascota`(`ID`, `NOMBRE`, `PROPIETARIO`, `ATENCION`, `COSTO`, `RAZA`) "
                + "VALUES (NULL,'%NOM%','%PROP%','%ATEN%','%COSTO%','%RAZA%');";

        SQL_Insertar = SQL_Insertar.replace("%NOM%", p.nombre);
        SQL_Insertar = SQL_Insertar.replace("%PROP%", p.propietario);
        SQL_Insertar = SQL_Insertar.replace("%ATEN%", p.atencion);
        SQL_Insertar = SQL_Insertar.replace("%COSTO%", String.valueOf(p.costo));
        SQL_Insertar = SQL_Insertar.replace("%RAZA%", p.raza);
        

        try {
            transaccion.execute(SQL_Insertar);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public ArrayList<Mascota> mostrarTodos() {
        ArrayList< Mascota> datos = new ArrayList<>();
        String SQL_consulta = "SELECT * FROM `Mascota`";

        try {
            //RESULTSET = variable que maneja las tuplas resultantes
            cursor = transaccion.executeQuery(SQL_consulta);

            if (cursor.next()) {
                do {
                    Mascota m = new Mascota(
                            cursor.getInt(1), // ID
                            cursor.getString(2), // Nombre
                            cursor.getString(3), // Propietario
                            cursor.getString(4), // Atención
                            cursor.getFloat(5), // costo
                            cursor.getString(6) //raza
                    );
                    datos.add(m);
                } while (cursor.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public Mascota obtenerPorID(String IDaBuscar) {
        String SQL_consulta = "SELECT * FROM `Mascota` WHERE `ID`=" + IDaBuscar;

        try {
            //RESULTSET = variable que maneja las tuplas resultantes
            cursor = transaccion.executeQuery(SQL_consulta);

            if (cursor.next()) {
                Mascota p = new Mascota(
                        cursor.getInt(1), // ID
                  cursor.getString(2), // Nombre
                            cursor.getString(3), // Propietario
                            cursor.getString(4), // Atención
                            cursor.getFloat(5), // costo
                            cursor.getString(6) //raza
                );
                return p;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Mascota(-1, "", "", "",-1.f,"");
    }

    public boolean eliminar(String IDaEliminar) {
        String SQL_eliminar = "DELETE FROM `Mascota` WHERE `ID`=" + IDaEliminar;

        try {
            transaccion.execute(SQL_eliminar);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Mascota p){
        String SQL_Actualizar = "UPDATE `Mascota` SET `NOMBRE`='%NOM%',`PROPIETARIO`='%PROP%',`ATENCION`='%ATEN%',"
                + "`COSTO`='%COSTO%',`RAZA`='%RAZA%' WHERE `ID`="+p.id;

        SQL_Actualizar = SQL_Actualizar.replace("%NOM%", p.nombre);
        SQL_Actualizar = SQL_Actualizar.replace("%PROP%", p.propietario);
        SQL_Actualizar = SQL_Actualizar.replace("%ATEN%", p.atencion);
        SQL_Actualizar = SQL_Actualizar.replace("%COSTO%", String.valueOf(p.costo));
        SQL_Actualizar = SQL_Actualizar.replace("%RAZA%", p.raza);

        try {
            transaccion.executeUpdate(SQL_Actualizar);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

}
