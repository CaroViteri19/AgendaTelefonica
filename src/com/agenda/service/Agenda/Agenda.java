package com.agenda.service.Agenda;

import com.agenda.model.contacto.contacto;

import java.util.*;

public class Agenda {
    private List<contacto> contactos;
    private int maximo;

    public Agenda(int maximo) {
        this.maximo = maximo > 0 ? maximo : 10;
        this.contactos = new ArrayList<>();
    }

    public boolean añadirContacto(contacto c) {
        if (agendaLlena()) return false;
        if (existeContacto(c)) return false;
        return contactos.add(c);
    }

    public boolean existeContacto(contacto c) {
        return contactos.contains(c);
    }

    public List<contacto> listarContactos() {
        List<contacto> copia = new ArrayList<>(contactos);
        copia.sort(Comparator.comparing(contacto::getNombre).thenComparing(contacto::getApellido));
        return copia;
    }

    public contacto buscarContacto(String nombre, String apellido) {
        for (contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarContacto(contacto c) {
        return contactos.remove(c);
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        contacto c = buscarContacto(nombre, apellido);
        if (c == null) return false;
        c.setTelefono(nuevoTelefono);
        return true;
    }

    public boolean agendaLlena() {
        return contactos.size() >= maximo;
    }

    public int espacioLibre() {
        return maximo - contactos.size();
    }
}

