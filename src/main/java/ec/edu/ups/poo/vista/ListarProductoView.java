package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Producto;
import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.controllers.Methods;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class ListarProductoView extends Frame {

    private List<Proveedor> proveedorList;
    private OpcionesView opcionesView;
    private TextArea displayArea;
    private Button btnBack;
    private Button btnBuscar;
    private TextField txtNombreProducto;
    private Label lblNombreProducto;
    private Methods methods;

    public ListarProductoView(List<Proveedor> proveedorList, OpcionesView opcionesView) {
        super("Listado y Búsqueda de Productos");
        this.proveedorList = proveedorList;
        this.opcionesView = opcionesView;
        this.methods = new Methods();

        setSize(700, 500);
        setLayout(new BorderLayout());


        Panel panelBusqueda = new Panel(new FlowLayout());
        lblNombreProducto = new Label("Nombre Producto:");
        txtNombreProducto = new TextField(20);
        btnBuscar = new Button("Buscar");
        panelBusqueda.add(lblNombreProducto);
        panelBusqueda.add(txtNombreProducto);
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

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


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = txtNombreProducto.getText().trim();

                if (nombreBusqueda.isEmpty()) {
                    mostrarMensaje("Por favor, ingrese el nombre del producto a buscar.");
                    return;
                }

                List<Producto> todosLosProductos = new ArrayList<>();
                for (Proveedor p : proveedorList) {
                    if (p.getProductos() != null) {
                        todosLosProductos.addAll(p.getProductos());
                    }
                }


                methods.ordenarProductosNombre(todosLosProductos);

                Producto productoEncontrado = methods.buscarProductoPorNombre(todosLosProductos, nombreBusqueda);

                if (productoEncontrado == null) {
                    mostrarMensaje("Producto con nombre '" + nombreBusqueda + "' no encontrado.");
                } else {

                    mostrarDetalleProductoEnNuevaVentana(productoEncontrado);
                }
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
                anyProducts = true;
                for (Producto prod : p.getProductos()) {
                    textoCompleto = textoCompleto + "    ID: " + prod.getId() + " | ";
                    textoCompleto = textoCompleto + "Nombre: " + prod.getNombreProducto() + " | ";
                    textoCompleto = textoCompleto + "Precio: $" + String.format("%.2f", prod.getPrecioProducto()) + " | ";
                    textoCompleto = textoCompleto + "Unidad: " + prod.getUnidadMedida() + " | ";
                    textoCompleto = textoCompleto + "Tipo: " + prod.getClass().getSimpleName() + "\n";
                }
            }
            textoCompleto = textoCompleto + "--------------------------------------------------\n\n";
        }

        if (!anyProducts && displayArea.getText().isEmpty()) {
            displayArea.setText("No hay productos registrados en ningún proveedor.");
        } else {
            displayArea.setText(textoCompleto);
        }
    }

    // --- NUEVO MÉTODO: Muestra el detalle del producto en una ventana nueva ---
    private void mostrarDetalleProductoEnNuevaVentana(Producto p) {
        Frame detalleFrame = new Frame("Detalle de Producto: " + p.getNombreProducto());
        detalleFrame.setSize(500, 300);
        detalleFrame.setLayout(new BorderLayout());

        TextArea detalleArea = new TextArea();
        detalleArea.setEditable(false);
        detalleFrame.add(detalleArea, BorderLayout.CENTER);

        String textoDetalle = "";
        textoDetalle = textoDetalle + "--- DETALLE DEL PRODUCTO ---\n\n";
        textoDetalle = textoDetalle + "  ID: " + p.getId() + "\n";
        textoDetalle = textoDetalle + "  Nombre: " + p.getNombreProducto() + "\n";
        textoDetalle = textoDetalle + "  Precio: $" + String.format("%.2f", p.getPrecioProducto()) + "\n";
        textoDetalle = textoDetalle + "  Unidad: " + p.getUnidadMedida() + "\n";
        textoDetalle = textoDetalle + "  Tipo: " + p.getClass().getSimpleName() + "\n";
        textoDetalle = textoDetalle + "  Proveedor: " + (p.getProveedor() != null ? p.getProveedor().getNombre() : "N/A") + "\n";
        textoDetalle = textoDetalle + "  -------------------------------------\n";
        detalleArea.setText(textoDetalle);

        Button cerrarButton = new Button("Cerrar");
        cerrarButton.addActionListener(e -> detalleFrame.dispose()); // Cierra solo esta ventana

        Panel botonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        botonPanel.add(cerrarButton);
        detalleFrame.add(botonPanel, BorderLayout.SOUTH);

        detalleFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                detalleFrame.dispose();
            }
        });

        detalleFrame.setLocationRelativeTo(this);
        detalleFrame.setVisible(true);
    }

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