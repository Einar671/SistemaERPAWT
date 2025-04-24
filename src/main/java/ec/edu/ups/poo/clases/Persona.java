package ec.edu.ups.poo.clases;

import java.util.Objects;

public class Persona {
    private String identificacion;
    private String nombre;
    private String email;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, String email) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(identificacion, persona.identificacion) && Objects.equals(nombre, persona.nombre) && Objects.equals(email, persona.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion, nombre, email);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
