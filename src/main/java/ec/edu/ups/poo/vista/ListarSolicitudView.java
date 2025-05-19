package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.DetalleCompra;
import ec.edu.ups.poo.modelo.clases.SolicitudCompra;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale; // Necesario para el formato de fecha

public class ListarSolicitudView extends Frame {

    private List<SolicitudCompra> solicitudesList; // La lista de solicitudes
    private OpcionesView opcionesView;
    private TextArea displayArea;
    private Button btnBack;
    // Para dar formato a la fecha, en español y con hora
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

    public ListarSolicitudView(List<SolicitudCompra> solicitudesList, OpcionesView opcionesView) {
        super("Listado de Solicitudes de Compra");
        this.solicitudesList = solicitudesList;
        this.opcionesView = opcionesView;

        setSize(700, 500);
        setLayout(new BorderLayout());

        displayArea = new TextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);

        btnBack = new Button("Volver");
        add(btnBack, BorderLayout.SOUTH);

        displaySolicitudes(); // Llama al método para mostrar las solicitudes

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

    // Método para obtener y mostrar la información de las solicitudes
    private void displaySolicitudes() {
        if (solicitudesList.isEmpty()) {
            displayArea.setText("No hay solicitudes de compra registradas.");
        } else {
            String textoCompleto = "";
            textoCompleto = textoCompleto + "--- LISTADO DE SOLICITUDES DE COMPRA ---\n\n";
            for (SolicitudCompra sol : solicitudesList) {
                textoCompleto = textoCompleto + "ID Solicitud: " + sol.getId() + "\n";
                // Muestra la fecha de la solicitud, si existe
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
}