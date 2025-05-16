package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Producto;
import ec.edu.ups.poo.modelo.clases.Proveedor;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListaDeProductosView {
    private Frame ventana;
    private Panel panelPrincipal;
    private Panel panelProveedores;
    private Panel panelProductos;
    private Panel panelBotones;
    private Label lblTitulo;
    private Label lblProveedor;
    private Label lblProductos;
    private Choice choiceProveedores;
    private TextArea txtAreaProductos;
    private Button btnAtras;

    public void mostrar() {
        ventana = new Frame("Lista de Productos por Proveedor");
        ventana.setSize(600, 400);
        ventana.setLayout(new BorderLayout());


        panelPrincipal = new Panel(new BorderLayout(10, 10));
        panelProveedores = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelProductos = new Panel(new BorderLayout());
        panelBotones = new Panel(new FlowLayout(FlowLayout.RIGHT));


        lblTitulo = new Label("LISTADO DE PRODUCTOS POR PROVEEDOR", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblProveedor = new Label("Seleccione un proveedor: ");
        lblProductos = new Label("Productos:");

        choiceProveedores = new Choice();
        cargarProveedores();

        // Área de texto para mostrar productos
        txtAreaProductos = new TextArea(15, 50);
        txtAreaProductos.setEditable(false);

        // Botón de regreso
        btnAtras = new Button("Atrás");

        // Añadir componentes a los paneles
        panelProveedores.add(lblProveedor);
        panelProveedores.add(choiceProveedores);

        panelProductos.add(lblProductos, BorderLayout.NORTH);
        panelProductos.add(txtAreaProductos, BorderLayout.CENTER);

        panelBotones.add(btnAtras);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelProveedores, BorderLayout.CENTER);
        panelPrincipal.add(panelProductos, BorderLayout.SOUTH);

        ventana.add(panelPrincipal, BorderLayout.CENTER);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        choiceProveedores.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarProductosProveedor();
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        // Cargar productos iniciales si hay proveedores
        if (choiceProveedores.getItemCount() > 0) {
            mostrarProductosProveedor();
        }
    }

    private void cargarProveedores() {
        List<Proveedor> proveedores = IngresoProovedorView.getListaProveedores();

        if (proveedores.isEmpty()) {
            choiceProveedores.add("No hay proveedores registrados");
            return;
        }

        for (Proveedor proveedor : proveedores) {
            choiceProveedores.add(proveedor.getNombre() + " - " + proveedor.getIdentificacion());
        }
    }

    private void mostrarProductosProveedor() {
        txtAreaProductos.setText("");
        List<Proveedor> proveedores = IngresoProovedorView.getListaProveedores();

        if (proveedores.isEmpty()) {
            return;
        }

        String seleccion = choiceProveedores.getSelectedItem();
        for (Proveedor proveedor : proveedores) {
            if (seleccion.contains(proveedor.getIdentificacion())) {
                List<Producto> productos = proveedor.getProductos();

                if (productos == null || productos.isEmpty()) {
                    txtAreaProductos.append("Este proveedor no tiene productos registrados.");
                    return;
                }

                txtAreaProductos.append("PRODUCTOS DE: " + proveedor.getNombre() + "\n");
                txtAreaProductos.append("--------------------------------------------------\n");

                for (Producto producto : productos) {
                    txtAreaProductos.append("ID: " + producto.getId() + "\n");
                    txtAreaProductos.append("Nombre: " + producto.getNombreProducto() + "\n");
                    txtAreaProductos.append("Precio: $" + producto.getPrecioProducto() + "\n");
                    txtAreaProductos.append("Unidad: " + producto.getUnidadMedida() + "\n");
                    txtAreaProductos.append("Precio calculado: $" + producto.calcularPrecio() + "\n");
                    txtAreaProductos.append("Tipo: " + producto.getClass().getSimpleName() + "\n");
                    txtAreaProductos.append("--------------------------------------------------\n");
                }

                break;
            }
        }
    }
    }