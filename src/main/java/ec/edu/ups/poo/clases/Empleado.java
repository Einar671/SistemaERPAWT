package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.Cargo;
import ec.edu.ups.poo.enums.UnidadMedida;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empleado extends Persona{
    private Departamento departamento;
    private Cargo cargo;
    private List<SolicitudCompra> solicitudes;

    public Empleado() {
        super();
        this.solicitudes = new ArrayList<>();
    }
    public Empleado(String identificacion, String nombre, String email, Departamento departamento, Cargo cargo) {
        super(identificacion, nombre, email);
        this.departamento = departamento;
        this.cargo = cargo;
        this.solicitudes = new ArrayList<>();
    }

    public List<SolicitudCompra> getSolicitudes() {
        return solicitudes;
    }
    public void addSolicitudes(SolicitudCompra solicitudes) {
        this.solicitudes.add(solicitudes);
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(departamento, empleado.departamento) && cargo == empleado.cargo && Objects.equals(solicitudes, empleado.solicitudes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departamento, cargo, solicitudes);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "departamento=" + departamento +
                ", cargo=" + cargo +
                ", solicitudes=" + solicitudes +
                '}';
    }
}
