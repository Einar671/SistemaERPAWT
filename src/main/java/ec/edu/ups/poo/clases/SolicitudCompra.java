package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoDeSolictud;
import ec.edu.ups.poo.interfaces.Calculable;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class SolicitudCompra implements Calculable {
    private int id;
    private GregorianCalendar fecha;
    private EstadoDeSolictud estado;
    private List<DetalleCompra> detalles;
    private double total;

    public SolicitudCompra() {
        this.detalles = new ArrayList<>();
    }

    public SolicitudCompra(int id, GregorianCalendar fecha, EstadoDeSolictud estado) {
        this.detalles = new ArrayList<>();
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this. total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public EstadoDeSolictud getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeSolictud estado) {
        this.estado = estado;
    }

    public void addDetalles(int codigo, int cantidad, String observaciones, Producto producto) {
        DetalleCompra detalle = new DetalleCompra(codigo, cantidad, observaciones, producto);
        this.detalles.add(detalle);
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }


    public double getTotal() {
        return total;
    }

    public EstadoDeSolictud gestionarEstado(EstadoDeSolictud estado){
        return estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudCompra that = (SolicitudCompra) o;
        return id == that.id && Objects.equals(fecha, that.fecha) && estado == that.estado && Objects.equals(detalles, that.detalles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, estado, detalles);
    }

    @Override
    public String toString() {
        return "SolicitudCompra{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", detalles=" + detalles +
                '}';
    }

    @Override
    public double calcularCostoTotal() {
        total = 0;
        for (DetalleCompra detalle : detalles) {
            total += detalle.calcularCostoTotal();
        }
        return total;
    }


}
