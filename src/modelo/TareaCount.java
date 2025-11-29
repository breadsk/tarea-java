/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nikom
 */
public class TareaCount {
    private int cantidadTareas;
    private int cantidadTareasActivas;
    private int cantidadTareasInactivas;

    public TareaCount(int cantidadTareas, int cantidadTareasActivas, int cantidadTareasInactivas) {
        this.cantidadTareas = cantidadTareas;
        this.cantidadTareasActivas = cantidadTareasActivas;
        this.cantidadTareasInactivas = cantidadTareasInactivas;
    }

    public int getCantidadTareas() {
        return cantidadTareas;
    }

    public void setCantidadTareas(int cantidadTareas) {
        this.cantidadTareas = cantidadTareas;
    }

    public int getCantidadTareasActivas() {
        return cantidadTareasActivas;
    }

    public void setCantidadTareasActivas(int cantidadTareasActivas) {
        this.cantidadTareasActivas = cantidadTareasActivas;
    }

    public int getCantidadTareasInactivas() {
        return cantidadTareasInactivas;
    }

    public void setCantidadTareasInactivas(int cantidadTareasInactivas) {
        this.cantidadTareasInactivas = cantidadTareasInactivas;
    }

    @Override
    public String toString() {
        return "TareaCount{" + "cantidadTareas=" + cantidadTareas + ", cantidadTareasActivas=" + cantidadTareasActivas + ", cantidadTareasInactivas=" + cantidadTareasInactivas + '}';
    }
    
    
}
