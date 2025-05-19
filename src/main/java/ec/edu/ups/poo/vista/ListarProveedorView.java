package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Proveedor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListarProveedorView extends Frame { // Hereda de Frame para ser una ventana

    private List<Proveedor> proveedorList; // La lista de proveedores que vamos a mostrar
    private OpcionesView opcionesView;     // Para poder volver a la ventana de Opciones
    private TextArea displayArea;          // Un área de texto grande para mostrar la información
    private Button btnBack;                // Botón para regresar

    public ListarProveedorView(List<Proveedor> proveedorList, OpcionesView opcionesView) {
        super("Listado de Proveedores"); // Título de la ventana
        this.proveedorList = proveedorList;
        this.opcionesView = opcionesView;

        setSize(600, 400); // Tamaño de la ventana
        setLayout(new BorderLayout()); // Usamos un diseño básico

        // Área de texto para mostrar la información
        displayArea = new TextArea();
        displayArea.setEditable(false); // No se puede escribir en ella
        add(displayArea, BorderLayout.CENTER); // La ponemos en el centro

        // Botón para volver
        btnBack = new Button("Volver");
        add(btnBack, BorderLayout.SOUTH); // Lo ponemos abajo

        // Llamamos al método que llena el área de texto con los proveedores
        displayProveedores();

        // Acción al hacer clic en el botón "Volver"
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);     // Oculta esta ventana
                opcionesView.mostrar(); // Muestra la ventana de Opciones
            }
        });

        // Acción al cerrar la ventana con la 'X'
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana
    }


    private void displayProveedores() {
        if (proveedorList.isEmpty()) {
            displayArea.setText("No hay proveedores registrados.");
        } else {
            String textoCompleto = ""; // Usamos una cadena para concatenar
            textoCompleto = textoCompleto + "--- LISTADO DE PROVEEDORES ---\n\n";
            for (Proveedor p : proveedorList) {
                textoCompleto = textoCompleto + "Identificación: " + p.getIdentificacion() + "\n";
                textoCompleto = textoCompleto + "Nombre: " + p.getNombre() + "\n";
                textoCompleto = textoCompleto + "Email: " + p.getEmail() + "\n";
                textoCompleto = textoCompleto + "Teléfono: " + p.getTelefono() + "\n";

                textoCompleto = textoCompleto + "Número de Productos: " + (p.getProductos() != null ? p.getProductos().size() : 0) + "\n";
                textoCompleto = textoCompleto + "----------------------------\n";
            }
            displayArea.setText(textoCompleto);
        }
    }
}