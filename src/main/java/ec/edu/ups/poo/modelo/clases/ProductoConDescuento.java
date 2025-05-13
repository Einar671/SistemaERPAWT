package ec.edu.ups.poo.modelo.clases;

import ec.edu.ups.poo.modelo.enums.UnidadMedida;

import java.util.Objects;

public class ProductoConDescuento extends Producto {
    private double descuetoPorcentaje;

    public ProductoConDescuento() {

    }

    public ProductoConDescuento(int id, String nombreProducto, double precioProducto, UnidadMedida unidadMedida, double descuetoPorcentaje) {
        super(id, nombreProducto, precioProducto, unidadMedida);
        this.descuetoPorcentaje = descuetoPorcentaje;
    }

    public double getDescuetoPorcentaje() {
        return descuetoPorcentaje;
    }

    public void setDescuetoPorcentaje(double descuetoPorcentaje) {
        this.descuetoPorcentaje = descuetoPorcentaje;
    }


    @Override
    public double calcularPrecio() {
        double respuesta = (getPrecioProducto())- descuetoPorcentaje * getPrecioProducto();
        return respuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductoConDescuento that = (ProductoConDescuento) o;
        return Double.compare(descuetoPorcentaje, that.descuetoPorcentaje) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descuetoPorcentaje);
    }

    @Override
    public String toString() {
        return "ProductoConDescuento{" +
                "descuetoPorcentaje=" + descuetoPorcentaje +
                '}';
    }
}
