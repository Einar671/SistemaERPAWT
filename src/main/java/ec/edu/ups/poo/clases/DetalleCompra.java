package ec.edu.ups.poo.clases;

import java.util.Objects;

public class DetalleCompra {
    private int codigo;
    private int cantidad;
    private String observaciones;
    private Producto producto;

    public DetalleCompra() {
    }

    public DetalleCompra(int codigo, int cantidad, String observaciones, Producto producto) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetalleCompra that = (DetalleCompra) o;
        return codigo == that.codigo && cantidad == that.cantidad && Objects.equals(observaciones, that.observaciones) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, cantidad, observaciones, producto);
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "codigo=" + codigo +
                ", cantidad=" + cantidad +
                ", observaciones='" + observaciones + '\'' +
                ", producto=" + producto +
                '}';
    }

}
