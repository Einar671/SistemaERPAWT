package ec.edu.ups.poo.vista;
import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.EstadoDeSolictud;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudDeCompraView extends Frame {

    private List<Proveedor> listaProveedores;
    private List<SolicitudCompra> listaSolicitudes;
    private SolicitudCompra solicitudActual;

    private Choice proveedorChoice;
    private Choice productoChoice;
    private TextField codigoDetalleField;
    private TextField cantidadField;
    private TextField observacionesField;
    private Button agregarDetalleButton;
    private Button finalizarSolicitudButton;
    private Button atrasButton;
    private TextArea detallesTextArea;

    public SolicitudDeCompraView(List<Proveedor> listaProveedores, List<SolicitudCompra> listaSolicitudes) {
        this.listaProveedores = listaProveedores;
        this.listaSolicitudes = listaSolicitudes;
        this.solicitudActual = new SolicitudCompra(listaSolicitudes.size() + 1,
                new GregorianCalendar(), EstadoDeSolictud.EN_REVISION);

        setTitle("Solicitud de Compra");
        setSize(600, 400);
        setLayout(new GridLayout(8, 2));

        add(new Label("Proveedor:"));
        proveedorChoice = new Choice();
        for (Proveedor p : listaProveedores) {
            proveedorChoice.add(p.getNombre());
        }
        add(proveedorChoice);

        add(new Label("Producto:"));
        productoChoice = new Choice();
        add(productoChoice);

        add(new Label("Código Detalle:"));
        codigoDetalleField = new TextField();
        add(codigoDetalleField);

        add(new Label("Cantidad:"));
        cantidadField = new TextField();
        add(cantidadField);

        add(new Label("Observaciones:"));
        observacionesField = new TextField();
        add(observacionesField);

        agregarDetalleButton = new Button("Agregar Detalle");
        add(agregarDetalleButton);

        add(new Label("Detalles de la Solicitud:"));
        detallesTextArea = new TextArea(5, 30);
        detallesTextArea.setEditable(false);
        add(detallesTextArea);

        finalizarSolicitudButton = new Button("Finalizar Solicitud");
        add(finalizarSolicitudButton);

        atrasButton = new Button("Atrás");
        add(atrasButton);

        proveedorChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                actualizarProductos();
            }
        });

        agregarDetalleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoStr = codigoDetalleField.getText();
                String cantidadStr = cantidadField.getText();
                String observaciones = observacionesField.getText();

                int codigo = Integer.parseInt(codigoStr);
                int cantidad = Integer.parseInt(cantidadStr);

                int indexProveedor = proveedorChoice.getSelectedIndex();
                Proveedor proveedorSeleccionado = listaProveedores.get(indexProveedor);
                int indexProducto = productoChoice.getSelectedIndex();
                Producto productoSeleccionado = proveedorSeleccionado.getProductos().get(indexProducto);

                solicitudActual.addDetalles(codigo, cantidad, observaciones, productoSeleccionado);
                actualizarDetallesTextArea();

                codigoDetalleField.setText("");
                cantidadField.setText("");
                observacionesField.setText("");
            }
        });

        finalizarSolicitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!solicitudActual.getDetalles().isEmpty()) {
                    listaSolicitudes.add(solicitudActual);
                    System.out.println("Solicitud finalizada y guardada: " + solicitudActual);
                    solicitudActual = new SolicitudCompra(listaSolicitudes.size() + 1,
                            new GregorianCalendar(), EstadoDeSolictud.EN_REVISION);
                    detallesTextArea.setText("");
                    setTitle("Solicitud de Compra (Nueva)");
                } else {
                    System.out.println("La solicitud debe tener al menos un detalle.");
                }
            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        actualizarProductos();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarProductos() {
        productoChoice.removeAll();
        int index = proveedorChoice.getSelectedIndex();
        if (index >= 0 && index < listaProveedores.size()) {
            Proveedor proveedor = listaProveedores.get(index);
            if (proveedor.getProductos() != null) {
                for (Producto p : proveedor.getProductos()) {
                    productoChoice.add(p.getNombreProducto());
                }
            }
        }
    }

    private void actualizarDetallesTextArea() {
        detallesTextArea.setText("");
        for (DetalleCompra detalle : solicitudActual.getDetalles()) {
            detallesTextArea.append("Código: " + detalle.getCodigo() + ", Producto: " + detalle.getProducto().getNombreProducto() + ", Cantidad: " + detalle.getCantidad() + ", Observaciones: " + detalle.getObservaciones() + "\n");
        }
    }
}