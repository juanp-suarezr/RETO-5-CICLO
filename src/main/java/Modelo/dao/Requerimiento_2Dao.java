package Modelo.dao;

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import Util.JDBCUtilities; 

import Modelo.vo.Requerimiento_2;

public class Requerimiento_2Dao {
    
    public ArrayList<Requerimiento_2> requerimiento2()  throws SQLException {
        ArrayList<Requerimiento_2> respuesta = new ArrayList<Requerimiento_2>();

        Connection conexion = JDBCUtilities.getConnection();

        try {

            String Consulta = "Select Nombre, Primer_Apellido, Ciudad_Residencia, Cargo, Salario " 
            + "from Lider where Salario < 510000 and Cargo= 'Asesor' or Cargo= 'Coordinador' and Salario < 510000;" ;

            PreparedStatement statement = conexion.prepareStatement(Consulta);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Requerimiento_2 requerimiento_2 = new Requerimiento_2();
                requerimiento_2.setNombre(resultset.getString("Nombre"));
                requerimiento_2.setPrimer_Apellido(resultset.getString("Primer_Apellido"));
                requerimiento_2.setCiudad_Residencia(resultset.getString("Ciudad_Residencia"));
                requerimiento_2.setCargo(resultset.getString("Cargo"));
                requerimiento_2.setSalario(resultset.getInt("Salario"));
                

                respuesta.add(requerimiento_2);
            }
            resultset.close();
            statement.close();

        } catch(SQLException e) {
            
            System.err.println("error consultado " +e);

        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return respuesta;
    }

}
