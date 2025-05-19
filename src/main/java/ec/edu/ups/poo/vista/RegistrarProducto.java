package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.UnidadMedida;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrarProducto extends Frame {

    private Proveedor proveedor;
    private RegistrarProveedor registrar;
    private List<Proveedor> proveedorList;
    private Panel panelPrincipal, panelCampos, panelBotones;
    private Label lblId, lblNombre, lblPrecio, lblUnidadMedida, lblTipoProducto, lblCampoExtra;
    private TextField txtId, txtNombre, txtPrecio, txtCampoExtra;
    private Choice choiceUnidadMedida, choiceTipoProducto;
    private Button btnGuardar, btnCerrar, btnFinalizarRegistro;
    private TextArea productosRegistrados;

    public RegistrarProducto(Proveedor proveedor, RegistrarProveedor registrar, List<Proveedor> proveedorList) {
        super("Registrar Producto");
        this.proveedor = proveedor;
        this.registrar = registrar;
        this.proveedorList = proveedorList;

        // Aseguramos que el proveedor tenga una lista de productos inicializada
        if (proveedor.getProductos() == null) {
            proveedor.setProductos(new ArrayList<>());
        }

        setSize(500, 550);
        setLayout(new BorderLayout(10, 10));

        panelPrincipal = new Panel(new BorderLayout(10, 10));
        panelCampos = new Panel(new GridLayout(7, 2, 5, 5));
        panelBotones = new Panel(new FlowLayout());

        lblId = new Label("ID:");
        txtId = new TextField();

        lblNombre = new Label("Nombre:");
        txtNombre = new TextField();

        lblPrecio = new Label("Precio:");
        txtPrecio = new TextField();

        lblUnidadMedida = new Label("Unidad Medida:");
        choiceUnidadMedida = new Choice();
        for (UnidadMedida u : UnidadMedida.values()) {
            choiceUnidadMedida.add(u.name());
        }

        lblTipoProducto = new Label("Tipo de Producto:");
        choiceTipoProducto = new Choice();
        choiceTipoProducto.add("Con IVA");
        choiceTipoProducto.add("Sin IVA");
        choiceTipoProducto.add("Con Descuento");

        lblCampoExtra = new Label("Porcentaje IVA:");
        txtCampoExtra = new TextField();

        productosRegistrados = new TextArea(5, 50);
        productosRegistrados.setEditable(false);

        panelCampos.add(lblId);
        panelCampos.add(txtId);
        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);
        panelCampos.add(lblPrecio);
        panelCampos.add(txtPrecio);
        panelCampos.add(lblUnidadMedida);
        panelCampos.add(choiceUnidadMedida);
        panelCampos.add(lblTipoProducto);
        panelCampos.add(choiceTipoProducto);
        panelCampos.add(lblCampoExtra);
        panelCampos.add(txtCampoExtra);

        btnGuardar = new Button("Guardar Producto");
        btnCerrar = new Button("Cancelar");
        btnFinalizarRegistro = new Button("Finalizar Registro");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnFinalizarRegistro);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelCampos, BorderLayout.NORTH);
        panelPrincipal.add(productosRegistrados, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        setLocationRelativeTo(null);
        setVisible(true);

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar.setVisible(true);
                registrar.limpiar();
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        choiceTipoProducto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String tipo = choiceTipoProducto.getSelectedItem();
                switch (tipo) {
                    case "Con IVA":
                        lblCampoExtra.setText("Porcentaje IVA (ej: 0.12):");
                        txtCampoExtra.setText("");
                        txtCampoExtra.setEnabled(true);
                        break;
                    case "Sin IVA":
                        lblCampoExtra.setText("Categoría Exención:");
                        txtCampoExtra.setText("");
                        txtCampoExtra.setEnabled(true);
                        break;
                    case "Con Descuento":
                        lblCampoExtra.setText("Porcentaje Descuento (ej: 0.10):");
                        txtCampoExtra.setText("");
                        txtCampoExtra.setEnabled(true);
                        break;
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                            txtPrecio.getText().isEmpty() || txtCampoExtra.getText().isEmpty()) {
                        mostrarMensaje("Todos los campos son obligatorios");
                        return;
                    }

                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    double precio = Double.parseDouble(txtPrecio.getText());
                    UnidadMedida unidadMedida = UnidadMedida.valueOf(choiceUnidadMedida.getSelectedItem());

                    String tipo = choiceTipoProducto.getSelectedItem();
                    Producto producto = null;

                    switch (tipo) {
                        case "Con IVA":
                            double iva = Double.parseDouble(txtCampoExtra.getText().trim());
                            producto = new ProductoConIva(id, nombre, precio, unidadMedida, iva);
                            break;
                        case "Sin IVA":
                            String categoria = txtCampoExtra.getText().trim();
                            producto = new ProductoSinIva(id, nombre, precio, unidadMedida, categoria);
                            break;
                        case "Con Descuento":
                            double descuento = Double.parseDouble(txtCampoExtra.getText().trim());
                            producto = new ProductoConDescuento(id, nombre, precio, unidadMedida, descuento);
                            break;
                    }

                    if (producto != null) {
                        producto.setProveedor(proveedor);
                        proveedor.getProductos().add(producto);
                        actualizarListaProductos();
                        limpiarCampos();
                        System.out.println("Producto agregado: " + producto);
                        mostrarMensaje("Producto agregado correctamente");
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensaje("Error en formato de números. Verifique ID, precio o porcentaje");
                } catch (Exception ex) {
                    mostrarMensaje("Error al guardar: " + ex.getMessage());
                }
            }
        });

        btnFinalizarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedor.getProductos() == null || proveedor.getProductos().isEmpty()) {
                    mostrarMensaje("Debe agregar al menos un producto");
                    return;
                }

                // Solo añadimos el proveedor a la lista si no existe ya
                boolean proveedorExistente = false;
                for (Proveedor p : proveedorList) {
                    if (p.getIdentificacion().equals(proveedor.getIdentificacion())) {
                        proveedorExistente = true;
                        break;
                    }
                }

                if (!proveedorExistente) {
                    proveedorList.add(proveedor);
                    System.out.println("Proveedor registrado completamente: " + proveedor);
                    System.out.println("Lista de proveedores actualizada, tamaño: " + proveedorList.size());

                    for (Proveedor p : proveedorList) {
                        System.out.println("- " + p.getNombre() + " con " +
                                (p.getProductos() != null ? p.getProductos().size() : 0) +
                                " productos");
                    }

                    mostrarMensaje("Proveedor y productos registrados correctamente");
                    registrar.setVisible(true);
                    registrar.limpiar();
                    setVisible(false);
                } else {
                    mostrarMensaje("Proveedor ya existe en el sistema");
                }
            }
        });

        // Actualizamos la lista de productos inicialmente
        actualizarListaProductos();
    }

    private void actualizarListaProductos() {
        productosRegistrados.setText("");
        if (proveedor.getProductos() != null && !proveedor.getProductos().isEmpty()) {
            productosRegistrados.append("Productos registrados para " + proveedor.getNombre() + ":\n\n");
            for (Producto p : proveedor.getProductos()) {
                productosRegistrados.append("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() +
                        " | Precio: $" + p.getPrecioProducto() + " | Tipo: " +
                        p.getClass().getSimpleName() + "\n");
            }
        } else {
            productosRegistrados.append("No hay productos registrados");
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

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCampoExtra.setText("");
    }
}