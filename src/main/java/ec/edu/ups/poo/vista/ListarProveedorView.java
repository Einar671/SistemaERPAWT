package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.controllers.Methods;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListarProveedorView extends Frame {

    private List<Proveedor> proveedorList;
    private OpcionesView opcionesView;
    private TextArea displayArea;
    private Button btnBack;
    private Button btnBuscar;
    private TextField txtIdProveedor;
    private Label lblIdProveedor;
    private Methods methods;

    public ListarProveedorView(List<Proveedor> proveedorList, OpcionesView opcionesView) {
        super("Listado y Búsqueda de Proveedores");
        this.proveedorList = proveedorList;
        this.opcionesView = opcionesView;
        this.methods = new Methods();

        setSize(700, 500);
        setLayout(new BorderLayout());


        Panel panelBusqueda = new Panel(new FlowLayout());
        lblIdProveedor = new Label("ID Proveedor:");
        txtIdProveedor = new TextField(15);
        btnBuscar = new Button("Buscar");
        panelBusqueda.add(lblIdProveedor);
        panelBusqueda.add(txtIdProveedor);
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);


        displayArea = new TextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);


        btnBack = new Button("Volver");
        add(btnBack, BorderLayout.SOUTH);


        displayProveedores();

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


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBusqueda = txtIdProveedor.getText().trim();

                if (idBusqueda.isEmpty()) {
                    mostrarMensaje("Por favor, ingrese la ID del proveedor a buscar.");
                    displayProveedores();
                    return;
                }

                methods.insertionSortIdentificacion(proveedorList);


                Proveedor proveedorEncontrado = methods.buscarProveedorPorIdentificacion(proveedorList, idBusqueda);

                if (proveedorEncontrado == null) {
                    mostrarMensaje("Proveedor con ID '" + idBusqueda + "' no encontrado.");
                    displayArea.setText("No se encontró ningún proveedor con la ID: " + idBusqueda);
                } else {
                    mostrarDetalleProveedor(proveedorEncontrado);
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayProveedores() {
        if (proveedorList.isEmpty()) {
            displayArea.setText("No hay proveedores registrados.");
        } else {
            String textoCompleto = "";
            textoCompleto = textoCompleto + "--- LISTADO DE PROVEEDORES ---\n\n";
            for (Proveedor p : proveedorList) {
                textoCompleto = textoCompleto + "Identificación: " + p.getIdentificacion() + "\n";
                textoCompleto = textoCompleto + "Nombre: " + p.getNombre() + "\n";
                textoCompleto = textoCompleto + "Email: " + p.getEmail() + "\n";
                textoCompleto = textoCompleto + "Teléfono: " + p.getTelefono() + "\n";
                textoCompleto = textoCompleto + "Número de Productos: " + (p.getProductos() != null ? p.getProductos().size() : 0) + "\n";
                textoCompleto = textoCompleto + "----------------------------\n";
            }
            displayArea.setText(textoCompleto);
        }
    }


    private void mostrarDetalleProveedor(Proveedor p) {
        String textoDetalle = "";
        textoDetalle = textoDetalle + "--- DETALLE DEL PROVEEDOR ENCONTRADO ---\n\n";
        textoDetalle = textoDetalle + "Identificación: " + p.getIdentificacion() + "\n";
        textoDetalle = textoDetalle + "Nombre: " + p.getNombre() + "\n";
        textoDetalle = textoDetalle + "Email: " + p.getEmail() + "\n";
        textoDetalle = textoDetalle + "Teléfono: " + p.getTelefono() + "\n";
        textoDetalle = textoDetalle + "Productos Asociados:\n";
        if (p.getProductos() == null || p.getProductos().isEmpty()) {
            textoDetalle = textoDetalle + "  - No tiene productos registrados.\n";
        } else {

            for (int i = 0; i < p.getProductos().size(); i++) {
                textoDetalle = textoDetalle + "    " + (i + 1) + ". " + p.getProductos().get(i).getNombreProducto() + " (ID: " + p.getProductos().get(i).getId() + ")\n";
            }
        }
        textoDetalle = textoDetalle + "--------------------------------------\n";
        displayArea.setText(textoDetalle);
    }

    // Método para mostrar mensajes en un diálogo simple
    private void mostrarMensaje(String mensaje) {
        Dialog dialog = new Dialog(this, "Mensaje", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label(mensaje));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.add(okButton);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}