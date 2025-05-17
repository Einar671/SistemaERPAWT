package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.*;
import ec.edu.ups.poo.modelo.controllers.Methods;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class RegistrarSolicitud extends Frame {

    private Empleado empleado;
    private List<Proveedor> proveedorList;
    private List<SolicitudCompra> solicitudesList;

    private Panel panelPrincipal;
    private Panel panelDatos;
    private Panel panelBotones;
    private Panel panelProductos;

    private Label lblTitulo;
    private Label lblId;
    private Label lblProveedor;
    private Label lblProducto;
    private Label lblCantidad;
    private Label lblObservaciones;

    private TextField txtId;
    private TextField txtCantidad;
    private TextField txtObservaciones;

    private Choice choiceProveedor;
    private Choice choiceProducto;

    private Button btnAgregarProducto;
    private Button btnGuardarSolicitud;
    private Button btnVolver;

    private TextArea areaDetalles;

    private SolicitudCompra solicitud;
    private List<Producto> productosDisponibles;
    private int codigoDetalle = 1;

    public RegistrarSolicitud(List<Proveedor> proveedorList, List<SolicitudCompra> solicitudesList, Empleado empleado, OpcionesView opcionesView) {
        super("Registrar Solicitud de Compra");

        this.proveedorList = proveedorList;
        this.solicitudesList = solicitudesList;
        this.empleado = empleado;
        this.productosDisponibles = new ArrayList<>();

        int idSolicitud = solicitudesList.size() + 1;
        solicitud = new SolicitudCompra(idSolicitud, new GregorianCalendar(), EstadoDeSolictud.EN_REVISION);

        setLayout(new BorderLayout(10, 10));
        panelPrincipal = new Panel(new BorderLayout(10, 10));

        panelDatos = new Panel(new GridLayout(6, 2, 5, 5));

        lblTitulo = new Label("SOLICITUD DE COMPRA", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        lblId = new Label("ID Solicitud:");
        txtId = new TextField(String.valueOf(idSolicitud));
        txtId.setEditable(false);

        lblProveedor = new Label("Seleccione Proveedor:");
        choiceProveedor = new Choice();
        choiceProveedor.add("-- Seleccione --");
        for (Proveedor p : proveedorList) {
            choiceProveedor.add(p.getNombre());
        }

        lblProducto = new Label("Seleccione Producto:");
        choiceProducto = new Choice();
        choiceProducto.add("-- Primero seleccione un proveedor --");

        lblCantidad = new Label("Cantidad:");
        txtCantidad = new TextField();

        lblObservaciones = new Label("Observaciones:");
        txtObservaciones = new TextField();

        panelDatos.add(lblId);
        panelDatos.add(txtId);
        panelDatos.add(lblProveedor);
        panelDatos.add(choiceProveedor);
        panelDatos.add(lblProducto);
        panelDatos.add(choiceProducto);
        panelDatos.add(lblCantidad);
        panelDatos.add(txtCantidad);
        panelDatos.add(lblObservaciones);
        panelDatos.add(txtObservaciones);

        panelBotones = new Panel(new FlowLayout());
        btnAgregarProducto = new Button("Agregar Producto");
        btnGuardarSolicitud = new Button("Guardar Solicitud");
        btnVolver = new Button("Volver");

        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnGuardarSolicitud);
        panelBotones.add(btnVolver);

        areaDetalles = new TextArea(10, 50);
        areaDetalles.setEditable(false);
        areaDetalles.append("Detalles de la solicitud #" + idSolicitud + ":\n\n");

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);

        panelProductos = new Panel(new BorderLayout());
        panelProductos.add(areaDetalles, BorderLayout.CENTER);
        panelProductos.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.NORTH);
        add(panelProductos, BorderLayout.CENTER);

        choiceProveedor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = choiceProveedor.getSelectedIndex();
                    if (index > 0) {
                        Proveedor proveedorSeleccionado = proveedorList.get(index - 1);
                        actualizarProductos(proveedorSeleccionado);
                    } else {
                        choiceProducto.removeAll();
                        choiceProducto.add("-- Primero seleccione un proveedor --");
                    }
                }
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choiceProveedor.getSelectedIndex() <= 0) {
                    mostrarMensaje("Debe seleccionar un proveedor");
                    return;
                }

                if (choiceProducto.getSelectedIndex() <= 0) {
                    mostrarMensaje("Debe seleccionar un producto");
                    return;
                }

                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    if (cantidad <= 0) {
                        mostrarMensaje("La cantidad debe ser mayor a cero");
                        return;
                    }

                    Producto productoSeleccionado = productosDisponibles.get(choiceProducto.getSelectedIndex() - 1);
                    String observaciones = txtObservaciones.getText();

                    solicitud.addDetalles(codigoDetalle, cantidad, observaciones, productoSeleccionado);

                    DetalleCompra ultimoDetalle = solicitud.getDetalles().get(solicitud.getDetalles().size() - 1);
                    double subtotal = ultimoDetalle.calcularCostoTotal();

                    areaDetalles.append("Detalle #" + codigoDetalle +
                            " | Producto: " + productoSeleccionado.getNombreProducto() +
                            " | Cantidad: " + cantidad +
                            " | Precio: $" + productoSeleccionado.getPrecioProducto() +
                            " | Subtotal: $" + subtotal + "\n");

                    codigoDetalle++;
                    limpiarCamposDetalle();
            }
        });

        btnGuardarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (solicitud.getDetalles().isEmpty()) {
                    mostrarMensaje("Debe agregar al menos un producto a la solicitud");
                    return;
                }

                double total = solicitud.calcularCostoTotal();
                areaDetalles.append("\nTOTAL DE LA SOLICITUD: $" + total + "\n");

                solicitudesList.add(solicitud);
                empleado.addSolicitudes(solicitud);

                mostrarMensaje("Solicitud #" + solicitud.getId() + " guardada con éxito. Total: $" + total);

                setVisible(false);
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
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

        pack();
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarProductos(Proveedor proveedor) {
        choiceProducto.removeAll();
        choiceProducto.add("-- Seleccione un producto --");

        productosDisponibles.clear();

        if (proveedor != null && proveedor.getProductos() != null && !proveedor.getProductos().isEmpty()) {
            for (Producto p : proveedor.getProductos()) {
                choiceProducto.add(p.getNombreProducto() + " - $" + p.getPrecioProducto());
                productosDisponibles.add(p);
            }
        } else {
            choiceProducto.add("No hay productos disponibles");
        }
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

    private void limpiarCamposDetalle() {
        txtCantidad.setText("");
        txtObservaciones.setText("");
        choiceProducto.select(0);
    }

    public List<SolicitudCompra> getSolicitud() {
        return solicitudesList;
    }
}