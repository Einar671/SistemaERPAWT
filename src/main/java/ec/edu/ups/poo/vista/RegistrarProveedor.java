package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class RegistrarProveedor extends Frame {

    private Panel panel1;
    private Panel panel2;
    private Label iden;
    private Label nombre;
    private Label email;
    private Label telefono;
    private Button atras;
    private Button ingresarpro;
    private TextField iden1;
    private TextField nombre1;
    private TextField email1;
    private TextField telefono1;

    private Proveedor proveedor;
    private java.util.List<Proveedor> proveedorList;

    public RegistrarProveedor(List<Proveedor> proveedorList) {
        super("Registrar Proveedor");
        this.proveedorList = proveedorList;
        proveedor = new Proveedor();

        panel1 = new Panel();
        panel2 = new Panel(new GridLayout(9, 1,5,5));

        iden = new Label("Identificacion:");
        nombre = new Label("Nombre:");
        email = new Label("Email:");
        telefono = new Label("Telefono:");

        iden1 = new TextField();
        nombre1 = new TextField();
        email1 = new TextField();
        telefono1 = new TextField();

        ingresarpro = new Button("Ingresar productos");
        atras = new Button("Atras");

        panel2.add(iden);
        panel2.add(iden1);
        panel2.add(nombre);
        panel2.add(nombre1);
        panel2.add(email);
        panel2.add(email1);
        panel2.add(telefono);
        panel2.add(telefono1);
        panel2.add(ingresarpro);

        panel1.add(panel2);
        panel1.add(atras);
        add(panel1);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        atras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcionesView op = new OpcionesView();
                op.mostrar();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

        ingresarpro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedor.setIdentificacion(iden1.getText());
                proveedor.setNombre(nombre1.getText());
                proveedor.setEmail(email1.getText());
                proveedor.setTelefono(telefono1.getText());
                proveedorList.add(proveedor);
                RegistrarProducto registrarProducto = new RegistrarProducto(proveedor,RegistrarProveedor.this,proveedorList);
                setVisible(false);
                System.out.println("Proveedor registrado: "+proveedor);
            }
        });
    }
    public void limpiar(){
        iden1.setText("");
        nombre1.setText("");
        email1.setText("");
        telefono1.setText("");
    }

}
