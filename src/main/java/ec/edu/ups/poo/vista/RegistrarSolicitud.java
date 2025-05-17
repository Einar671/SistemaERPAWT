package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.EstadoDeSolictud;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import java.util.List;

public class RegistrarSolicitud extends Frame {

    private List<Proveedor> listaProveedores;
    private List<SolicitudCompra> solicitudes;
    private SolicitudCompra solicitud;


    private Choice proveedorChoice;
    private Choice productoChoice;
    private TextField codigoDetalleField;
    private TextField cantidadField;
    private TextField observacionesField;
    private Button agregarDetalleButton;
    private Button finalizarSolicitudButton;
    private Button atrasButton;
    private TextArea detallesTextArea;

    public RegistrarSolicitud(List<Proveedor> listaProveedores, List<SolicitudCompra> solicitudes, Empleado empleado) {
        this.listaProveedores = listaProveedores;
        this.solicitudes = solicitudes;
        this.solicitud = new SolicitudCompra(solicitudes.size() + 1,
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

        setLocationRelativeTo(null);
        setVisible(true);

        atrasButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                OpcionesView c = new OpcionesView();
                c.mostrar();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        proveedorChoice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                actualizarProductos();
            }
        });

        agregarDetalleButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
                Integer codigo = Integer.parseInt(codigoDetalleField.getText());
                Integer cantidad = Integer.parseInt(cantidadField.getText());
                String observaciones = observacionesField.getText();

               int indexProveedor = proveedorChoice.getSelectedIndex();
               Proveedor proveedorSeleccionado = listaProveedores.get(indexProveedor);
               int indexProducto = productoChoice.getSelectedIndex();
               Producto productoSeleccionado = proveedorSeleccionado.getProductos().get(indexProducto);

               solicitud.addDetalles(codigo, cantidad, observaciones, productoSeleccionado);
               actualizarDetalles();

               codigoDetalleField.setText("");
               cantidadField.setText("");
               observacionesField.setText("");
           }
        });
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

    public void actualizarDetalles(){
        detallesTextArea.setText("");
        for (DetalleCompra detalle : solicitud.getDetalles()) {
            detallesTextArea.append("Código: " + detalle.getCodigo() + ", Producto: " + detalle.getProducto().getNombreProducto() + ", Cantidad: " + detalle.getCantidad() + ", Observaciones: " + detalle.getObservaciones() + "\n");
        }
    }
}
