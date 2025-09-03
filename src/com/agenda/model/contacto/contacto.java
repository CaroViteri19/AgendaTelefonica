package com.agenda.model.contacto;

public class contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    public contacto(String nombre, String apellido, String telefono) {
        if (nombre.isBlank() || apellido.isBlank())
            throw new IllegalArgumentException("Nombre y apellido obligatorios");
        if (!telefono.matches("\\d{10}")) throw new IllegalArgumentException("Teléfono inválido (10 dígitos)");

        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String nuevoTelefono) {
        if (!nuevoTelefono.matches("\\d{10}")) throw new IllegalArgumentException("Teléfono inválido");
        this.telefono = nuevoTelefono;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof contacto)) return false;
        contacto otro = (contacto) obj;
        return nombre.equalsIgnoreCase(otro.nombre) && apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}

