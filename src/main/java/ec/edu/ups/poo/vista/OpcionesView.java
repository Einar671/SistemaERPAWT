package ec.edu.ups.poo.vista;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.Cargo;

import java.util.List;
public class OpcionesView {
    private Empleado empleado = new Empleado("0150204212","Harald Kaalhus","email",new Departamento(), Cargo.AUXILIAR);
    private Frame ventana;
    private Panel panel1;
    private Panel panel2;
    private Panel panel3;
    private Button botonatras;
    private Label label1;
    private Button rproovedor;
    private Button rsolicitud;
    private Button listarprove;
    private Button listarprodu;
    private Button listarsolicitud;

    private Button manejarsoli;
    private Button calcular;
    private List<Proveedor> proveedorList;
    private List<SolicitudCompra> solicitudes;

    public OpcionesView() {
        proveedorList = new ArrayList<>();
        solicitudes = new ArrayList<>();
    }

    public OpcionesView(Frame ventana, Panel panel1, Panel panel2, Panel panel3, Button botonatras, Label label1, Button rproovedor, Button rsolicitud, Button listarprove, Button listarprodu, Button listarsolicitud, Button buscarproid, Button buscarproductonom, Button buscarsolicitud, Button manejarsoli, Button calcular) {
        this.ventana = ventana;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.botonatras = botonatras;
        this.label1 = label1;
        this.rproovedor = rproovedor;
        this.rsolicitud = rsolicitud;
        this.listarprove = listarprove;
        this.listarprodu = listarprodu;
        this.listarsolicitud = listarsolicitud;

        this.manejarsoli = manejarsoli;
        this.calcular = calcular;
        proveedorList = new ArrayList<>();
        solicitudes = new ArrayList<>();
    }

    public void mostrar() {

        ventana = new Frame("Opciones del sistema");
        panel1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        panel2 = new Panel(new GridLayout(3, 4, 10, 10));
        panel3 = new Panel(new FlowLayout());

        label1 = new Label("INGRESE UNA OPCIÓN:");
        botonatras = new Button("ATRÁS");

        panel3.add(label1);
        panel3.add(botonatras);

        rproovedor = new Button("REGISTRAR PROVEEDOR");
        rsolicitud = new Button("REGISTRAR SOLICITUD");
        listarprove = new Button("LISTAR PROVEEDOR");
        listarprodu = new Button("LISTAR PRODUCTOS");
        listarsolicitud = new Button("LISTAR SOLICITUD");
        manejarsoli = new Button("APROBAR/RECHAZAR SOLICITUD");
        calcular = new Button("CALCULAR TOTAL");

        panel2.add(rproovedor);
        panel2.add(rsolicitud);
        panel2.add(listarprove);
        panel2.add(listarprodu);
        panel2.add(listarsolicitud);

        panel2.add(manejarsoli);
        panel2.add(calcular);

        panel1.add(panel3);
        panel1.add(panel2);
        ventana.add(panel1);
        botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                InicioView inicioView = new InicioView();
                inicioView.mostrar();
            }
        });
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        rproovedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                RegistrarProveedor rprov = new RegistrarProveedor(proveedorList);

            }
        });

        listarprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
            }
        });

        rsolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                RegistrarSolicitud rs = new RegistrarSolicitud(proveedorList,solicitudes,empleado);
            }
        });

        listarprodu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);

            }
        });

        listarsolicitud.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ventana.setSize(1000, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}