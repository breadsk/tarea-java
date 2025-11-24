/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas Cáceres Latorre
 */
public class TareaDAO {
    
        public boolean guardarTarea(Tarea tarea){
            Connection connection = null;
            PreparedStatement ps = null;
            
            try{
                connection = Conexion.getConnection();
                String sql = "INSERT INTO tarea(nombre,descripcion,estado) "
                        + "VALUES(?,?,?)";
                
                ps = connection.prepareCall(sql);
                ps.setString(1, tarea.getNombre());
                ps.setString(2, tarea.getDescripcion());
                ps.setBoolean(3, tarea.getEstado() != null ? tarea.getEstado() : true);
                
                int resultado = ps.executeUpdate();
                
                return resultado > 0;
            
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Error al guardar: " + ex.getMessage(),
                        "Error",JOptionPane.ERROR);
                return false;
            } finally {
                cerrarRecursos(connection, ps, null);
            }
        
        }
 
        
        public Tarea buscarTareaPorId(int id){
            
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Tarea tarea = null;
            
            try{
               
                connection = Conexion.getConnection();
                String sql = "SELECT * FROM tarea WHERE id = ?";
                
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    tarea = new Tarea();
                    tarea.setId(rs.getInt("id"));
                    tarea.setNombre(rs.getString("nombre"));
                    tarea.setDescripcion(rs.getString("descripcion"));
                    tarea.setEstado(rs.getBoolean("estado"));
                }
                
            }catch(SQLException ex){
                System.out.println("Error al buscar: " + ex.getMessage());
            }finally{
                cerrarRecursos(connection, ps, rs);
            }
            return tarea;
        }
        
        
        public boolean actualizarTarea(Tarea tarea){
        
            Connection connection = null;
            PreparedStatement ps = null;
            
            try{
            
                connection = Conexion.getConnection();
                String sql = "UPDATE tarea SET nombre = ?, "
                        + "descripcion = ?, estado = ? WHERE id = ?";
                
                ps = connection.prepareStatement(sql);
                ps.setString(1,tarea.getNombre());
                ps.setString(2, tarea.getDescripcion());
                ps.setBoolean(3, tarea.getEstado());
                ps.setInt(4, tarea.getId());
                
                int resultado = ps.executeUpdate();
                return resultado > 0;
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {
                cerrarRecursos(connection, ps, null);
            }
        }
        
        
        public List<Tarea> listarTarea(){
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            List<Tarea> tareas = new ArrayList<>();
            try{
                connection = Conexion.getConnection();
                String sql = "SELECT * FROM tarea";
                
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Tarea tarea = new Tarea(
                       rs.getInt("id"),
                       rs.getString("nombre"),
                       rs.getString("descripcion"),
                       rs.getBoolean("estado")
                    );
                    tareas.add(tarea);
                }
                
                
            }catch(SQLException ex){
                System.out.println("Error al listar: "+ ex.getMessage());
            } finally{
                cerrarRecursos(connection, ps, rs);
            }
            return tareas;
        }
        
        
        public boolean eliminarTarea(int id){
            Connection connection = null;
            PreparedStatement ps = null;
            
            try{
                
                connection = Conexion.getConnection();
                String sql = "DELETE FROM tarea WHERE id = ?";
                
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                
                int resultado = ps.executeUpdate();
                return resultado > 0;
                
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
               return false;
            } finally {
                cerrarRecursos(connection, ps, null);
            }
        }
        
        private void cerrarRecursos(Connection connection,PreparedStatement ps , ResultSet rs){
        
            try{
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(connection != null && !connection.isClosed()) connection.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
}
