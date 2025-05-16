package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.clases.SolicitudCompra;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaDeSolicitudView {
    private Frame ventana;
    private Panel panelPrincipal, panelSeleccion, panelDatos, panelBotones;
    private Label lblProveedor, lblSolicitudes, lblDetalles;
    private Choice choiceProveedores, choiceSolicitudes;
    private TextArea txtAreaDetalles;
    private Button btnVolver;

    private List<Proveedor> listaProveedores;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ListaDeSolicitudView() {
        this.listaProveedores = IngresoProovedorView.getListaProveedores();
    }

    public void mostrar() {
        ventana = new Frame("Lista de Solicitudes");
        ventana.setSize(550, 500);
        ventana.setLayout(new BorderLayout(10, 10));

        panelSeleccion = new Panel(new GridLayout(4, 1, 5, 5));

        lblProveedor = new Label("Seleccione un Proveedor:");
        choiceProveedores = new Choice();

        cargarProveedores();

        lblSolicitudes = new Label("Seleccione una Solicitud:");
        choiceSolicitudes = new Choice();

        panelSeleccion.add(lblProveedor);
        panelSeleccion.add(choiceProveedores);
        panelSeleccion.add(lblSolicitudes);
        panelSeleccion.add(choiceSolicitudes);

        panelDatos = new Panel(new BorderLayout());
        lblDetalles = new Label("Detalles de la Solicitud:");
        txtAreaDetalles = new TextArea(10, 50);
        txtAreaDetalles.setEditable(false);

        panelDatos.add(lblDetalles, BorderLayout.NORTH);
        panelDatos.add(txtAreaDetalles, BorderLayout.CENTER);

        panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new Button("Volver");
        panelBotones.add(btnVolver);

        panelPrincipal = new Panel(new BorderLayout(10, 10));
        panelPrincipal.add(panelSeleccion, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        ventana.add(panelPrincipal, BorderLayout.CENTER);

        choiceProveedores.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cargarSolicitudesPorProveedor();
            }
        });

        choiceSolicitudes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarDetallesSolicitud();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        if (choiceProveedores.getItemCount() > 0) {
            cargarSolicitudesPorProveedor();
        }

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void cargarProveedores() {
        choiceProveedores.removeAll();

        if (listaProveedores == null || listaProveedores.isEmpty()) {
            choiceProveedores.add("No hay proveedores registrados");
            return;
        }

        for (Proveedor proveedor : listaProveedores) {
            choiceProveedores.add(proveedor.getNombre() + " (" + proveedor.getIdentificacion() + ")");
        }
    }

    private void cargarSolicitudesPorProveedor() {
        choiceSolicitudes.removeAll();
        txtAreaDetalles.setText("");

        if (listaProveedores == null || listaProveedores.isEmpty()) {
            choiceSolicitudes.add("No hay solicitudes disponibles");
            return;
        }

        int indiceProveedor = choiceProveedores.getSelectedIndex();
        if (indiceProveedor < 0 || indiceProveedor >= listaProveedores.size()) {
            choiceSolicitudes.add("No hay solicitudes disponibles");
            return;
        }

        Proveedor proveedorSeleccionado = listaProveedores.get(indiceProveedor);
        boolean solicitudesEncontradas = false;

        for (int i = 0; i < proveedorSeleccionado.getProductos().size(); i++) {
            if (proveedorSeleccionado.getProductos().get(i).getProveedor() != null) {

                choiceSolicitudes.add("Solicitud #" + (i+1) + " - Producto: " +
                        proveedorSeleccionado.getProductos().get(i).getNombreProducto());
                solicitudesEncontradas = true;
            }
        }

        if (!solicitudesEncontradas) {
            choiceSolicitudes.add("No hay solicitudes para este proveedor");
        }
    }

    private void mostrarDetallesSolicitud() {
        if (choiceSolicitudes.getSelectedItem().startsWith("No hay")) {
            txtAreaDetalles.setText("No hay detalles disponibles");
            return;
        }

        int indiceProveedor = choiceProveedores.getSelectedIndex();
        if (indiceProveedor < 0 || indiceProveedor >= listaProveedores.size()) {
            return;
        }

        Proveedor proveedorSeleccionado = listaProveedores.get(indiceProveedor);

        // Obtenemos el índice del producto en el formato "Solicitud #X - Producto: Y"
        String seleccion = choiceSolicitudes.getSelectedItem();
        int indiceInicio = seleccion.indexOf("#") + 1;
        int indiceFin = seleccion.indexOf(" - ");

        if (indiceInicio < 0 || indiceFin < 0) {
            txtAreaDetalles.setText("No se pueden mostrar los detalles");
            return;
        }

            int indiceProducto = Integer.parseInt(seleccion.substring(indiceInicio, indiceFin)) - 1;

            if (indiceProducto >= 0 && indiceProducto < proveedorSeleccionado.getProductos().size()) {
                StringBuilder detalles = new StringBuilder();
                detalles.append("Detalles de la Solicitud:\n\n");
                detalles.append("Proveedor: ").append(proveedorSeleccionado.getNombre()).append("\n");
                detalles.append("Identificación: ").append(proveedorSeleccionado.getIdentificacion()).append("\n");
                detalles.append("Producto: ").append(proveedorSeleccionado.getProductos().get(indiceProducto).getNombreProducto()).append("\n");
                detalles.append("Precio: $").append(proveedorSeleccionado.getProductos().get(indiceProducto).getPrecioProducto()).append("\n");
                detalles.append("Precio calculado: $").append(proveedorSeleccionado.getProductos().get(indiceProducto).calcularPrecio()).append("\n");
                detalles.append("Unidad de medida: ").append(proveedorSeleccionado.getProductos().get(indiceProducto).getUnidadMedida()).append("\n");
                detalles.append("Tipo de producto: ").append(proveedorSeleccionado.getProductos().get(indiceProducto).getClass().getSimpleName()).append("\n");

                txtAreaDetalles.setText(detalles.toString());
            } else {
                txtAreaDetalles.setText("No se encontró el producto seleccionado");
            }
    }
}