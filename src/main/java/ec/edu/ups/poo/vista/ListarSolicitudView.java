package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.DetalleCompra;
import ec.edu.ups.poo.modelo.clases.SolicitudCompra;
import ec.edu.ups.poo.modelo.controllers.Methods;
import ec.edu.ups.poo.modelo.enums.EstadoDeSolictud;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale; // Necesario para el formato de fecha

public class ListarSolicitudView extends Frame {

    private List<SolicitudCompra> solicitudesList;
    private Panel panelGeneral;
    private Panel panelSolicitud;
    private Panel panelBotones;
    private OpcionesView opcionesView;
    private TextArea displayArea;
    private Label texto;
    private Button rechazar;
    private Button aceptar;
    private Button buscar;
    private TextField IdSolicitud;
    private Button btnBack;
    private Methods methods;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

    public ListarSolicitudView(List<SolicitudCompra> solicitudesList, OpcionesView opcionesView) {
        super("Listado de Solicitudes de Compra");
        this.solicitudesList = solicitudesList;
        this.opcionesView = opcionesView;

        setSize(700, 500);
        setLayout(new BorderLayout());
        methods = new Methods();
        panelGeneral = new Panel(new BorderLayout());
        panelSolicitud = new Panel(new BorderLayout());
        panelBotones = new Panel();
        aceptar = new Button("Aceptar");
        rechazar = new Button("Rechazar");
        IdSolicitud = new TextField(10);
        texto = new Label("ID Solicitud: ");
        btnBack = new Button("Volver");
        buscar = new Button("Buscar");

        panelBotones.add(texto);
        panelBotones.add(IdSolicitud);
        panelBotones.add(buscar);
        panelBotones.add(btnBack);

        displayArea = new TextArea();
        displayArea.setEditable(false);
        panelSolicitud.add(displayArea, BorderLayout.CENTER);

        panelGeneral.add(panelBotones, BorderLayout.NORTH);
        panelGeneral.add(panelSolicitud, BorderLayout.CENTER);
        add(panelGeneral);
        displaySolicitudes();

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

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (solicitudesList.isEmpty()) {
                    mostrarMensaje("No hay solicitudes disponibles");
                } else if (IdSolicitud.getText().equals("")) {
                    mostrarMensaje("No se ha seleccionado una ID de solicitudes");
                } else {
                    methods.ordenarSolicitudesPorNumero(solicitudesList);
                    SolicitudCompra solicitudCompraEncontrada = methods.buscarSolicitudesPorNumero(solicitudesList, Integer.parseInt(IdSolicitud.getText()));
                    if (solicitudCompraEncontrada == null) {
                        mostrarMensaje("No se ha encontrado la solicitud");
                    } else {
                        Frame frame = new Frame("Detalle de Solicitud");
                        frame.setLayout(new BorderLayout());

                        Panel panelContenido = new Panel(new BorderLayout());
                        Panel panelBotones = new Panel(new FlowLayout());

                        TextArea textArea = new TextArea();
                        textArea.setEditable(false);

                        StringBuilder detalleTexto = new StringBuilder();
                        detalleTexto.append("ID solicitud: ").append(solicitudCompraEncontrada.getId()).append("\n");
                        detalleTexto.append("Fecha: ").append(
                                solicitudCompraEncontrada.getFecha() != null ?
                                        dateFormat.format(solicitudCompraEncontrada.getFecha().getTime()) : "N/A"
                        ).append("\n");
                        detalleTexto.append("Estado: ").append(solicitudCompraEncontrada.getEstado()).append("\n");
                        detalleTexto.append("Detalles:\n");

                        if (solicitudCompraEncontrada.getDetalles() == null || solicitudCompraEncontrada.getDetalles().isEmpty()) {
                            detalleTexto.append("  - No tiene detalles\n");
                        } else {
                            for (DetalleCompra det : solicitudCompraEncontrada.getDetalles()) {
                                detalleTexto.append("  Código Detalle: ").append(det.getCodigo()).append("\n");
                                detalleTexto.append("  Producto: ").append(det.getProducto().getNombreProducto()).append("\n");
                                detalleTexto.append("  Cantidad: ").append(det.getCantidad()).append("\n");
                                detalleTexto.append("  Precio Unitario: $").append(String.format("%.2f", det.getProducto().getPrecioProducto())).append("\n");
                                detalleTexto.append("  Subtotal: $").append(String.format("%.2f", det.calcularCostoTotal())).append("\n");
                                detalleTexto.append("  Observaciones: ").append(det.getObservaciones()).append("\n");
                                detalleTexto.append("  -----------------------------\n");
                            }
                        }

                        detalleTexto.append("\nTotal Solicitud: $").append(String.format("%.2f", solicitudCompraEncontrada.calcularCostoTotal())).append("\n");

                        textArea.setText(detalleTexto.toString());

                        // Botones
                        Button btnAceptar = new Button("Aceptar");
                        Button btnRechazar = new Button("Rechazar");

                        btnAceptar.addActionListener(ev -> {
                            solicitudCompraEncontrada.setEstado(EstadoDeSolictud.APROVADA);
                            mostrarMensaje("Solicitud aprobada");
                            frame.dispose();
                            displayArea.setText("");
                            displaySolicitudes();
                        });

                        btnRechazar.addActionListener(ev -> {
                            solicitudCompraEncontrada.setEstado(EstadoDeSolictud.RECHAZADA);
                            mostrarMensaje("Solicitud rechazada");
                            frame.dispose();
                            displayArea.setText("");
                            displaySolicitudes();
                        });

                        panelBotones.add(btnRechazar);
                        panelBotones.add(btnAceptar);

                        panelContenido.add(textArea, BorderLayout.CENTER);
                        frame.add(panelContenido, BorderLayout.CENTER);
                        frame.add(panelBotones, BorderLayout.SOUTH);

                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            }
        });
    }

    private void displaySolicitudes() {
        if (solicitudesList.isEmpty()) {
            displayArea.setText("No hay solicitudes de compra registradas.");
        } else {
            String textoCompleto = "";
            textoCompleto = textoCompleto + "--- LISTADO DE SOLICITUDES DE COMPRA ---\n\n";
            for (SolicitudCompra sol : solicitudesList) {
                textoCompleto = textoCompleto + "ID Solicitud: " + sol.getId() + "\n";
                textoCompleto = textoCompleto + "Fecha: " + (sol.getFecha() != null ? dateFormat.format(sol.getFecha().getTime()) : "N/A") + "\n";
                textoCompleto = textoCompleto + "Estado: " + sol.getEstado() + "\n";
                textoCompleto = textoCompleto + "Detalles:\n";
                if (sol.getDetalles() == null || sol.getDetalles().isEmpty()) {
                    textoCompleto = textoCompleto + "  - Sin detalles de productos.\n";
                } else {
                    for (DetalleCompra det : sol.getDetalles()) {
                        textoCompleto = textoCompleto + "    Código Detalle: " + det.getCodigo() + "\n";
                        textoCompleto = textoCompleto + "    Producto: " + det.getProducto().getNombreProducto() + "\n";
                        textoCompleto = textoCompleto + "    Cantidad: " + det.getCantidad() + "\n";
                        textoCompleto = textoCompleto + "    Precio Unitario: $" + String.format("%.2f", det.getProducto().getPrecioProducto()) + "\n";
                        textoCompleto = textoCompleto + "    Subtotal: $" + String.format("%.2f", det.calcularCostoTotal()) + "\n";
                        textoCompleto = textoCompleto + "    Observaciones: " + det.getObservaciones() + "\n";
                        textoCompleto = textoCompleto + "    ---\n";
                    }
                }
                // Muestra el total de la solicitud
                textoCompleto = textoCompleto + "Total Solicitud: $" + String.format("%.2f", sol.calcularCostoTotal()) + "\n";
                textoCompleto = textoCompleto + "========================================\n\n";
            }
            displayArea.setText(textoCompleto);
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
}