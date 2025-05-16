package ec.edu.ups.poo.vista;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import ec.edu.ups.poo.modelo.clases.*;
import java.util.List;
public class OpcionesView {

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
    private Button buscarproid;
    private Button buscarproductonom;
    private Button buscarsolicitud;
    private Button manejarsoli;
    private Button calcular;

    // Lista para almacenar las solicitudes de compra
    private static java.util.List<SolicitudCompra> listaSolicitudes = new ArrayList<>();

    public OpcionesView() {

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
        this.buscarproid = buscarproid;
        this.buscarproductonom = buscarproductonom;
        this.buscarsolicitud = buscarsolicitud;
        this.manejarsoli = manejarsoli;
        this.calcular = calcular;
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
        buscarproid = new Button("BUSCAR PROVEEDOR");
        buscarproductonom = new Button("BUSCAR PRODUCTOS");
        buscarsolicitud = new Button("BUSCAR SOLICITUD");
        manejarsoli = new Button("APROBAR/RECHAZAR SOLICITUD");
        calcular = new Button("CALCULAR TOTAL");

        panel2.add(rproovedor);
        panel2.add(rsolicitud);
        panel2.add(listarprove);
        panel2.add(listarprodu);
        panel2.add(listarsolicitud);
        panel2.add(buscarproid);
        panel2.add(buscarproductonom);
        panel2.add(buscarsolicitud);
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
                IngresoProovedorView ingresoProovedorView = new IngresoProovedorView();
                ingresoProovedorView.mostrar();
            }
        });

        listarprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                ListaProovedoresView listaProvedoresView = new ListaProovedoresView();
                listaProvedoresView.mostrar();
            }
        });

        rsolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                List<Proveedor> listaProveedores = IngresoProovedorView.getListaProveedores();
                SolicitudDeCompraView solicitudView = new SolicitudDeCompraView(listaProveedores, listaSolicitudes);
            }
        });

        ventana.setSize(1000, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}