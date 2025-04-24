package ec.edu.ups.poo.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proveedor extends Persona {
    private List<Producto> productos;
    private String telefono;

    public Proveedor() {
        this.productos = new ArrayList<Producto>();
    }

    public Proveedor(String identificacion, String nombre, String email, String telefono) {
        super(identificacion, nombre, email);
        this.telefono = telefono;
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void addProductos(Producto productos) {
        this.productos.add(productos);
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
