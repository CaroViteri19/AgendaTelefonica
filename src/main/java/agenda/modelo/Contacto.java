package agenda.modelo;


import java.util.Objects;

public class Contacto implements Comparable<Contacto> {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public int compareTo(Contacto otro) {
        int cmp = this.nombre.compareToIgnoreCase(otro.nombre);
        if (cmp == 0) {
            cmp = this.apellido.compareToIgnoreCase(otro.apellido);
        }
        return cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;
        Contacto contacto = (Contacto) o;
        return nombre.equalsIgnoreCase(contacto.nombre)
                && apellido.equalsIgnoreCase(contacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}