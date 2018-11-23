
package excel_a_mysql;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.JFileChooser;
//import java.text.SimpleDateFormat;  
//import java.util.Date; 


/**
 *
 * @author PCMED-JEBERRIO
 */
public class Excel_a_Mysql {
    
        /*
        java.util.Date utilDate = new java.util.Date(); //fecha actual    
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date Fecha = new java.sql.Date(lnMilisegundos);*/
        Conexion con;
	String IdCompania;
	String Sector;
	String Area;
	String Familia;
	String Cliente;
	String Articulo;
	String Articulodescripcion;
	String Boleta;
	//Date Fecha;
        String Fecha;
	String Permanencia;
	String Saldo;
	String Ventasanuales;
	String Peso;
	String Costo;
 
    private void LeerExcel(String Destiny) throws SQLException, ClassNotFoundException, ParseException
    {	
        int flag = 1;
        try{
            Workbook archivoexcel = Workbook.getWorkbook(new File(Destiny));
            
            for (int cont = 0; cont < archivoexcel.getNumberOfSheets(); cont++)
            //for (int cont = 0; cont <= 1; cont++)
            {
                Sheet hojas = archivoexcel.getSheet(cont);
                int columnas = hojas.getColumns();
                int filas = hojas.getRows();
                String datos;
                
                for (int fila = 1; fila < filas; fila++)
                //for (int fila = 1; fila < 2; fila++)    
                {
                    for (int columna = 0; columna < columnas; columna++)
                    //for (int columna = 0; columna < 1; columnas++)
                    {
                        datos = hojas.getCell(columna, fila).getContents();
                        System.out.print(datos + " " + "\n");
                        
                        switch(flag)
                        {
                            case 1: 
                                IdCompania = datos;
                                flag++;
                                break;
                            
                            case 2: 
                                Sector = datos;
                                flag++;
                                break;
                                
                            case 3: 
                                Area = datos;
                                flag++;
                                break;
                                
                            case 4: 
                                Familia = datos;
                                flag++;
                                break;
                                
                            case 5: 
                                Cliente = datos;
                                flag++;
                                break;
                            
                            case 6: 
                                Articulo = datos;
                                flag++;
                                break;
                                
                            case 7: 
                                Articulodescripcion = datos;
                                flag++;
                                break;
                                
                            case 8: 
                                Boleta = datos;
                                flag++;
                                break; 
                                
                            case 9: 
                                //Fecha = new SimpleDateFormat("dd/MM/yyyy").parse(datos);
                                Fecha = datos;
                                flag++;
                                System.out.println("salida de fecha:"+Fecha);
                                break;
                            
                            case 10: 
                                Permanencia = datos;
                                flag++;
                                break;
                                
                            case 11: 
                                Saldo = datos;
                                flag++;
                                break;
                                
                            case 12: 
                                Ventasanuales = datos;
                                flag++;
                                break;   
                                
                            case 13: 
                                Peso = datos;
                                flag++;
                                break;
                            
                            case 14: 
                                Costo = datos;
                                flag = 1;
                                break;                        
                        }                        
                    }
                    //flag = 1;
                    System.out.println("\n");
                    con = new Conexion();
                    Connection con2 = con.conectar();
                    String codigo = "INSERT INTO guardarinventario(Idcompania, Sector, Area, Familia, Cliente, Articulo, Articulodescripcion, Boleta, Fecha, Permanencia, Saldo, Ventasanuales, Peso, Costo) VALUES ('"+IdCompania+"','"+Sector+"','"+Area+"','"+Familia+"','"+Cliente+"','"+Articulo+"','"+Articulodescripcion+"','"+Boleta+"','"+Fecha+"','"+Permanencia+"','"+Saldo+"','"+Ventasanuales+"','"+Peso+"','"+Costo+"');";
                    PreparedStatement pst = con2.prepareStatement(codigo);
                    pst.executeUpdate();
                    con.Close_conexion();
                }
            }    
        }catch (IOException | BiffException ioe)
        {
        } 
            
    }
    
    public  static void main(String[] args) throws Exception {
        
        
        Scanner entrada = null;
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual
        JFileChooser fileChooser = new JFileChooser(".");      
        //Se crea el filtro. El primer parámetro es el mensaje que se muestra,
        //el segundo es la extensión de los ficheros que se van a mostrar      
        //FileFilter filtro = new FileNameExtensionFilter("Archivos Excel (.xls)", "Excel"); 
        //Se le asigna al JFileChooser el filtro
        //fileChooser.setFileFilter(filtro);
        //se muestra la ventana
        int valor = fileChooser.showOpenDialog(fileChooser);
        if (valor == JFileChooser.APPROVE_OPTION)
        {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                File f = new File(ruta);
                entrada = new Scanner(f);
                while (entrada.hasNext()) {
                    System.out.println(entrada.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (entrada != null) {
                    entrada.close();
                    Excel_a_Mysql archivo = new Excel_a_Mysql();
            try
            {
                archivo.LeerExcel(ruta);
            } catch (SQLException | ClassNotFoundException exi)
            {
                Logger.getLogger(Excel_a_Mysql.class.getName()).log(Level.SEVERE, null, exi);
            }
                }
            }
        } else {
                    System.out.println("No se ha seleccionado ningún fichero");
                }
    }    
}    


