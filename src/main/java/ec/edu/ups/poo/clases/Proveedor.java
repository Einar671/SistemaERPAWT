package ec.edu.ups.poo.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proveedor extends Persona {
    private List<Producto> productos;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(String identificacion, String nombre, String email, List<Producto> productos, String telefono) {
        super(identificacion, nombre, email);
        this.productos = productos;
        this.telefono = telefono;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(productos, proveedor.productos) && Objects.equals(telefono, proveedor.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productos, telefono);
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "productos=" + productos +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
