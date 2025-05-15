package ec.edu.ups.poo.vista;



import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.controllers.Methods;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaProovedoresView extends Frame {

    private Panel panelgeneral;
    private Panel botones;
    private Panel listprovedores;
    private Button atras;
    private Button ordenar;
    private TextArea textArea;
    private Label label;

    public void mostrar() {
        List<Proveedor> listaProovedores = new IngresoProovedorView().getListaProveedores();

        panelgeneral = new Panel(new GridLayout(1,2));
        botones = new Panel();
        listprovedores = new Panel();

        label = new Label("Lista de provedores", Label.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        textArea = new TextArea(20,60);
        textArea.setEditable(false);

        atras = new Button("Atras");
        ordenar = new Button("Ordenar por identificación");


        listprovedores.add(label, BorderLayout.NORTH);
        listprovedores.add(textArea, BorderLayout.CENTER);

        botones.add(atras);
        botones.add(ordenar);

        panelgeneral.add(botones, BorderLayout.SOUTH);
        panelgeneral.add(listprovedores, BorderLayout.CENTER);

        add(panelgeneral);

        mostrarProovedores(listaProovedores);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });

        ordenar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Methods methods = new Methods();
                methods.insertionSortIdentificacion(listaProovedores);
                mostrarProovedores(listaProovedores);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }

    public void mostrarProovedores(List<Proveedor> listaProvedores) {
        textArea.setText("");


        if (listaProvedores==null || listaProvedores.isEmpty()) {
            textArea.append("No proveedores encontrados");
        }else {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("%-15s %-25s %-25s %-15s %-15s\n",
                    "INDENTIFIACION", "NOMBRE", "EMAIL", "TELÉFONO", "PRODUCTOS"));
            stringBuilder.append("--------------------------------------------------\n");

            for (Proveedor proveedor : listaProvedores) {
                int numProductos = (proveedor.getProductos() != null) ? proveedor.getProductos().size() : 0;
                stringBuilder.append(String.format("%-15s %-25s %-25s %-15s %-15d\n",
                        proveedor.getIdentificacion(),
                        proveedor.getNombre(),
                        proveedor.getEmail(),
                        proveedor.getTelefono(),
                        numProductos));
            }
            textArea.append(stringBuilder.toString());
        }
    }

}
