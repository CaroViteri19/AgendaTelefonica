package logica;

import java.util.Objects;

public class Contacto {
    //Agregamos atributos privados
    private String nombre;
    private String apellido;
    private String telefono;

    //Agregamos el constructor
    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    //Getters: valores de los atributos
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTelefono() {
        return telefono;
    }

    //Setters: permite cambiar el valor - acceder a los valores de los atributos

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10}")){
            throw new IllegalArgumentException("Ingrese un teléfono válido");
        }
        this.telefono = telefono;
    }
    //Para ver cómo se va a mostrar
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + "\n" + "Telefono: " + telefono;
    }

    //Para que dos contactos no sean iguales/ no se repitan

    @Override
    public boolean equals (Object object) {
        if (this == object) return true;
        if (object == null || getClass() !=object.getClass()) return false;

        Contacto otro = (Contacto) object;
        return nombre.equalsIgnoreCase(otro.nombre) && apellido.equalsIgnoreCase(otro.apellido);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }
}
