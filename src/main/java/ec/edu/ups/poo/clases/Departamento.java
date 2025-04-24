package ec.edu.ups.poo.clases;

import java.util.List;
import java.util.Objects;

public class Departamento {
    private String nombreDepartamento;
    private String descripcionDepartamento;
    private double presupuest;
    private List<Empleado> empleados;

    public Departamento() {
    }

    public Departamento(String nombreDepartamento, String descripcionDepartamento, double presupuest, List<Empleado> empleados) {
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
        this.presupuest = presupuest;
        this.empleados = empleados;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public double getPresupuest() {
        return presupuest;
    }

    public void setPresupuest(double presupuest) {
        this.presupuest = presupuest;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Double.compare(presupuest, that.presupuest) == 0 && Objects.equals(nombreDepartamento, that.nombreDepartamento) && Objects.equals(descripcionDepartamento, that.descripcionDepartamento) && Objects.equals(empleados, that.empleados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreDepartamento, descripcionDepartamento, presupuest, empleados);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nombreDepartamento='" + nombreDepartamento + '\'' +
                ", descripcionDepartamento='" + descripcionDepartamento + '\'' +
                ", presupuest=" + presupuest +
                ", empleados=" + empleados +
                '}';
    }
}
