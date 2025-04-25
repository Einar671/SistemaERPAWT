package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.UnidadMedida;

import java.util.Objects;

public abstract class Producto {
    private int id;
    private String nombreProducto;
    private double precioProducto;
    private UnidadMedida unidadMedida;
    private Proveedor proveedor;

    public Producto() {

    }

    public Producto(int id, String nombreProducto, double precioProducto, UnidadMedida unidadMedida) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.unidadMedida = unidadMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public abstract double calcularPrecio();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Double.compare(precioProducto, producto.precioProducto) == 0 && Objects.equals(nombreProducto, producto.nombreProducto) && unidadMedida == producto.unidadMedida && Objects.equals(proveedor, producto.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreProducto, precioProducto, unidadMedida, proveedor);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioProducto=" + precioProducto +
                ", unidadMedida=" + unidadMedida +
                ", proveedor=" + proveedor +
                '}';
    }
}
