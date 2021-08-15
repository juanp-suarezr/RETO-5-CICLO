package Modelo.dao;

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import Util.JDBCUtilities;

import Modelo.vo.Requerimiento_1;

public class Requerimiento_1Dao {   

    //METODO RETORNA UN ARRAY TIPO REQUERIMIENTO
    public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
        ArrayList<Requerimiento_1> respuesta = new ArrayList<Requerimiento_1>();

        Connection conexion = JDBCUtilities.getConnection();

        try {

            String Consulta = "SELECT ID_Proyecto, Ciudad, Banco_Vinculado, Constructora, Clasificacion " 
            + "from Proyecto where Clasificacion = 'Condominio' AND Fecha_Inicio Between '2020-01-01' and '2021-12-24';";

            PreparedStatement statement = conexion.prepareStatement(Consulta);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Requerimiento_1 requerimiento_1 = new Requerimiento_1();
                requerimiento_1.setID_Proyecto(resultset.getInt("ID_Proyecto"));
                requerimiento_1.setCiudad(resultset.getString("Ciudad"));
                requerimiento_1.setBanco_vinculado(resultset.getString("Banco_Vinculado"));
                requerimiento_1.setConstructora(resultset.getString("Constructora"));
                requerimiento_1.setClasificacion(resultset.getString("Clasificacion"));

                respuesta.add(requerimiento_1);
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