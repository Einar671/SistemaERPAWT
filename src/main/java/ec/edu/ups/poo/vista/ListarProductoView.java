package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Producto;
import ec.edu.ups.poo.modelo.clases.Proveedor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListarProductoView extends Frame {

    private List<Proveedor> proveedorList;
    private OpcionesView opcionesView;
    private TextArea displayArea;
    private Button btnBack;

    public ListarProductoView(List<Proveedor> proveedorList, OpcionesView opcionesView) {
        super("Listado de Productos por Proveedor");
        this.proveedorList = proveedorList;
        this.opcionesView = opcionesView;

        setSize(700, 500);
        setLayout(new BorderLayout());


        displayArea = new TextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);

        btnBack = new Button("Volver");
        add(btnBack, BorderLayout.SOUTH);

        displayProductos();

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                opcionesView.mostrar();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayProductos() {
        if (proveedorList.isEmpty()) {
            displayArea.setText("No hay proveedores registrados, por lo tanto, no hay productos.");
            return;
        }

        String textoCompleto = "";
        textoCompleto = textoCompleto + "--- LISTADO DE PRODUCTOS POR PROVEEDOR ---\n\n";

        boolean anyProducts = false;
        for (Proveedor p : proveedorList) {
            textoCompleto = textoCompleto + "PROVEEDOR: " + p.getNombre() + " (ID: " + p.getIdentificacion() + ")\n";
            if (p.getProductos() == null || p.getProductos().isEmpty()) {
                textoCompleto = textoCompleto + "  - No tiene productos registrados.\n";
            } else {
                anyProducts = true; // Sí hay productos
                for (Producto prod : p.getProductos()) {
                    textoCompleto = textoCompleto + "    ID: " + prod.getId() + " | ";
                    textoCompleto = textoCompleto + "Nombre: " + prod.getNombreProducto() + " | ";
                    textoCompleto = textoCompleto + "Precio: $" + String.format("%.2f", prod.getPrecioProducto()) + " | ";
                    textoCompleto = textoCompleto + "Unidad: " + prod.getUnidadMedida() + " | ";
                    textoCompleto = textoCompleto + "Tipo: " + prod.getClass().getSimpleName() + "\n"; // Muestra si es ConIva, SinIva, etc.
                }
            }
            textoCompleto = textoCompleto + "--------------------------------------------------\n\n";
        }

        if (!anyProducts) {
            displayArea.setText("No hay productos registrados en ningún proveedor.");
        } else {
            displayArea.setText(textoCompleto);
        }
    }
}