package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.enums.UnidadMedida;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
public class RegistrarProducto extends Frame {

    private Proveedor proveedor;
    private RegistrarProveedor registrar;
    private Panel panelPrincipal, panelCampos, panelBotones;
    private Label lblId, lblNombre, lblPrecio, lblUnidadMedida, lblTipoProducto, lblCampoExtra;
    private TextField txtId, txtNombre, txtPrecio, txtCampoExtra;
    private Choice choiceUnidadMedida, choiceTipoProducto;
    private Button btnGuardar, btnCerrar;


    public RegistrarProducto(Proveedor proveedor, RegistrarProveedor registrar) {
        super("Registrar Producto");
        this.proveedor = proveedor;
        this.registrar = registrar;
        setSize(400, 400);
        setLayout(new BorderLayout());

        panelPrincipal = new Panel(new BorderLayout());
        panelCampos = new Panel(new GridLayout(6, 2, 5, 5));
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
        btnCerrar = new Button("Cerrar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
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

        btnGuardar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                UnidadMedida unidadMedida =UnidadMedida.valueOf(choiceUnidadMedida.getSelectedItem());

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

                if(producto != null){
                    producto.setProveedor(proveedor);
                    proveedor.getProductos().add(producto);
                    limpiarCampos();
                    System.out.println("Producto agregado: " + producto);
                }
            }
        });
    }
    private void limpiarCampos(){
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCampoExtra.setText("");
    }

}
