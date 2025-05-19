package ec.edu.ups.poo.vista;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List; // Asegúrate de que sea java.util.List
import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.Cargo;

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
    private List<Proveedor> proveedorList; // Lista de proveedores
    private List<SolicitudCompra> solicitudes; // Lista de solicitudes

    public OpcionesView() {
        proveedorList = new ArrayList<>();
        solicitudes = new ArrayList<>();
    }

    // Este constructor no es necesario si siempre usas el constructor sin argumentos
    // o si lo usas, asegúrate de que todos los parámetros se manejen correctamente.
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
        proveedorList = new ArrayList<>(); // Asegúrate de inicializar las listas
        solicitudes = new ArrayList<>();   // si no se pasan por parámetro
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

        // --- Manejadores de Eventos (Action Listeners) ---

        // Botón ATRÁS
        botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                InicioView inicioView = new InicioView();
                inicioView.mostrar(); // Muestra la ventana de Inicio
            }
        });

        // Cerrar ventana con la 'X'
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        // Botón REGISTRAR PROVEEDOR
        rproovedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                // Creamos una nueva ventana de RegistrarProveedor
                // y le pasamos la lista de proveedores actual y esta misma ventana (OpcionesView)
                RegistrarProveedor rprov = new RegistrarProveedor(proveedorList, OpcionesView.this);
            }
        });

        // --- Botón LISTAR PROVEEDOR ---
        listarprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                // Creamos una nueva ventana para listar proveedores
                // Le pasamos la lista de proveedores y esta misma ventana (OpcionesView)
                ListarProveedorView listarProv = new ListarProveedorView(proveedorList, OpcionesView.this);
            }
        });

        // Botón REGISTRAR SOLICITUD
        rsolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                // Creamos una nueva ventana para registrar solicitudes
                // Le pasamos las listas de proveedores, solicitudes, el empleado y esta ventana
                RegistrarSolicitud rs = new RegistrarSolicitud(proveedorList, solicitudes, empleado, OpcionesView.this);
            }
        });

        // --- Botón LISTAR PRODUCTOS ---
        listarprodu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                // Creamos una nueva ventana para listar productos
                // Le pasamos la lista de proveedores (para que pueda acceder a sus productos) y esta ventana
                ListarProductoView listarProd = new ListarProductoView(proveedorList, OpcionesView.this);
            }
        });

        // --- Botón LISTAR SOLICITUD ---
        listarsolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false); // Oculta esta ventana
                // Creamos una nueva ventana para listar solicitudes
                // Le pasamos la lista de solicitudes y esta misma ventana (OpcionesView)
                ListarSolicitudView listarSol = new ListarSolicitudView(solicitudes, OpcionesView.this);
            }
        });

        // Puedes añadir aquí los Action Listeners para 'manejarsoli' y 'calcular' de forma similar.

        ventana.setSize(1000, 400); // Establece el tamaño de la ventana
        ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        ventana.setVisible(true); // Hace visible la ventana
    }
}