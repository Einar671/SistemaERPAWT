package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
                ventanaPrincipal.setVisible(false); // opcional: esconder ventana actual
                OpcionesView opciones = new OpcionesView();
                opciones.mostrar();
            }
        });
        ventanaPrincipal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
