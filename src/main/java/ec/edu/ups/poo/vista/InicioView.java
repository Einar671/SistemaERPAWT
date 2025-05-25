package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.*;

public class InicioView {

    private Frame ventanaPrincipal;
    private Panel ventanaPrincipalPanel;
    private Panel ventanaSolicitudPanel;
    private Button botonInicio;
    private Label lblSolicitud;
    public InicioView() {
    }

    public InicioView(Frame ventanaPrincipal, Panel ventanaPrincipalPanel, Panel ventanaSolicitudPanel, Button botonInicio, Label lblSolicitud, OpcionesView opcionesView) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.ventanaPrincipalPanel = ventanaPrincipalPanel;
        this.ventanaSolicitudPanel = ventanaSolicitudPanel;
        this.botonInicio = botonInicio;
        this.lblSolicitud = lblSolicitud;
    }

    public void mostrar() {
        ventanaPrincipal = new Frame("Sistema ERP");
        ventanaPrincipalPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        ventanaSolicitudPanel = new Panel(new GridLayout(2,1));
        botonInicio = new Button("Ingresar ->");
        lblSolicitud = new Label("SISTEMA ERP", Label.CENTER);

        ventanaSolicitudPanel.add(lblSolicitud);
        ventanaSolicitudPanel.add(botonInicio);

        ventanaPrincipalPanel.add(ventanaSolicitudPanel);
        ventanaPrincipal.add(ventanaPrincipalPanel);

        ventanaPrincipal.setSize(500, 500);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
        botonInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(false);
                OpcionesView opciones = new OpcionesView();
                opciones.mostrar();
            }
        });
        ventanaPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }
}
