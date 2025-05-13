package ec.edu.ups.poo.modelo.clases;

import ec.edu.ups.poo.modelo.enums.EstadoDeSolictud;
import ec.edu.ups.poo.modelo.interfaces.Calculable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class SolicitudCompra implements Calculable {
    private int id;
    private GregorianCalendar fecha;
    private EstadoDeSolictud estado;
    private List<DetalleCompra> detalles;


    public SolicitudCompra() {
        this.detalles = new ArrayList<>();
    }

    public SolicitudCompra(int id, GregorianCalendar fecha, EstadoDeSolictud estado) {
        this.detalles = new ArrayList<>();
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
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
        return calcularCostoTotal(); // Devuelve el total calculado dinámicamente
    }

    public EstadoDeSolictud gestionarEstado(EstadoDeSolictud estado){
        this.estado = estado;
        return this.estado;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = (fecha != null) ? sdf.format(fecha.getTime()) : "Fecha no disponible";

        return "SolicitudCompra{" +
                "id=" + id +
                ", fecha=" + fechaFormateada +
                ", estado=" + estado +
                ", detalles=" + detalles +
                '}';
    }

    @Override
    public double calcularCostoTotal() {
        double totalCalculado = 0;
        for (DetalleCompra detalle : detalles) {
            totalCalculado += detalle.calcularCostoTotal();
        }
        return totalCalculado;
    }
}