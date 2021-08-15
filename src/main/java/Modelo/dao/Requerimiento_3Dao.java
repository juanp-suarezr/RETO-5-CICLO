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

import Modelo.vo.Requerimiento_3;

public class Requerimiento_3Dao {
    //Obtener los 10 proyectos rankeados según las compras
    public ArrayList<Requerimiento_3> requerimiento3()  throws SQLException {
        ArrayList<Requerimiento_3> respuesta = new ArrayList<Requerimiento_3>();

        Connection conexion = JDBCUtilities.getConnection();

        try {

            String Consulta = "select C.Proveedor, C.Pagado, P.Constructora "
            + "from Compra C inner join Proyecto P on C.ID_Proyecto= P.ID_Proyecto where C.Pagado = 'No' and C.Proveedor = 'JUMBO' ;";

            PreparedStatement statement = conexion.prepareStatement(Consulta);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Requerimiento_3 requerimiento_3 = new Requerimiento_3();
                requerimiento_3.setProveedor(resultset.getString("Proveedor"));
                requerimiento_3.setPagado(resultset.getString("Pagado"));
                requerimiento_3.setConstructora(resultset.getString("Constructora"));
                

                respuesta.add(requerimiento_3);
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
