package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Producto;
import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.clases.SolicitudCompra;

import java.awt.*;
import java.util.List;

public class ListaDeSolicitudView {

    private Frame ventana;
    private Panel panelgeneral;
    private Panel solicitudes;
    private Panel proveedor;
    private Panel titulo;
    private Choice opciones;
    private Button atras;
    private TextArea listaSolicitud;
    private Label tituloLabel;
    private Label solicitud;
    private Label proveedores;

    public void mostrar(){}

    public void cargarProveedores() {
        List<Proveedor> proveedors = new IngresoProovedorView().getListaProveedores();

        if (proveedors.isEmpty()) {
            opciones.add("No hay proveedores");
        }

        for (Proveedor proveedor : proveedors) {
            opciones.addItem(proveedor.getNombre()+"-->"+proveedor.getIdentificacion());
        }
    }

    public void mostrarSolicitud(){
        listaSolicitud.setText("");

        List<Proveedor> proveedors = new IngresoProovedorView().getListaProveedores();

        if (proveedors.isEmpty()) {
            opciones.add("No hay proveedores");
        }

        String selecccion = opciones.getSelectedItem();
        for (Proveedor proveedor : proveedors) {
            if (selecccion.contains(proveedor.getIdentificacion())) {
            }
        }



    }
}
