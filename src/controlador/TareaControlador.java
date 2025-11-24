/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Tarea;
import modelo.TareaDAO;
import excepciones.TareaException;

/**
 *
 * @author Nicolás Cáceres 
 */
public class TareaControlador {
    
    private TareaDAO tareaDAO;
       
    public TareaControlador() {
        this.tareaDAO = new TareaDAO();
    }
    public boolean crearTarea(String nombre,String descripcion) throws TareaException {
    
        //1. Vamos a validar los datos
        validarDatosTarea(nombre, descripcion);
        
        //2. Vamos a crear la iunstancia del modelo
        Tarea tarea = new Tarea(nombre, descripcion,true);
        
        //3. Usar el DAO para guardar
        if(tareaDAO.guardarTarea(tarea)){
            return true;
        }else{
            throw new TareaException("No se pudo guardar la tarea en la base de datos");
        }
    }
    
    
    public Tarea buscarTareaPorId(int id){
        return tareaDAO.buscarTareaPorId(id);
    }
    
    public String actualizarTarea(int id,String nombre,String descripcion,boolean estado){
        
        //1.Creamos el objeto tarea con los datos entregados
        Tarea tarea = new Tarea(id,nombre,descripcion,estado);
        
        // Usar el DAO para actualizar la tarea
        if(tareaDAO.actualizarTarea(tarea)) {
            return "Tarea editada satisfactoriamente";
        } else {
            return "No se pudo actualizar la tarea, revise los datos";
        }
        
    }
    
    public List<Tarea> listarTareas() throws TareaException {
        List<Tarea> tareas = tareaDAO.listarTarea();
        
        if(tareas.isEmpty()){
            throw new TareaException("No se encontraron tareas en la base de datos");
        }
        
        return tareas;
    }
    
    public boolean eliminarTarea(int id){
        return tareaDAO.eliminarTarea(id);
    }
    
    private void validarDatosTarea(String nombre,String descripcion) throws TareaException{
        if(nombre == null || nombre.trim().isEmpty()){
            throw new TareaException("El nombre de la tarea no puede estar vacio");
        }
        if(descripcion == null || descripcion.trim().isEmpty()){
            throw new TareaException("La descripcion de la tarea no puede estar vacía");
        }
        
        
        //Validar la longitud del texto
        if(nombre.length() > 100 ){
            throw new TareaException("EL nombre de la tarea no puede tener mas de 100 caracteres");
        }
        if(descripcion.length() > 300){
            throw new TareaException("La descripcion de la tarea no puede tener mas de 300 caracteres");                    
        }
    }
    
}
