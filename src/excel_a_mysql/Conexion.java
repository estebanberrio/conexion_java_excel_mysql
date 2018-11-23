
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel_a_mysql;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PCMED-JEBERRIO
 */
public class Conexion {
    
    Connection conect = null;
    public Connection conectar() throws ClassNotFoundException
    {
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                conect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ws","root","");
                System.out.println("Conexion éxitosa!");
            }catch (ClassNotFoundException | SQLException e)    
                {
                       System.out.println("Error"+e);
                }
        return conect;
    }
    
    public Connection Close_conexion()
    {
	try{
		conect.close();
		System.out.println("Conexion Cerrada!");
	}catch (SQLException ex)
		{
			System.out.println(ex);
		}
	conect = null;
	return conect;
    }
    
}
