package agenda.modelo;

import agenda.exceptions.*;

import java.util.Arrays;

public class Agenda extends AgendaBase {

    public Agenda() {
        super(10);
    }

    public Agenda(int capacidad) {
        super(capacidad);
    }

    @Override
    public void anadirContacto(Contacto c) throws AgendaLlenaException, ContactoDuplicadoException, DatosInvalidosException {
        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) {
            throw new DatosInvalidosException("Nombre y apellido no pueden estar vacíos.");
        }
        if (existeContacto(c)) {
            throw new ContactoDuplicadoException("El contacto ya existe.");
        }
        if (agendaLlena()) {
            throw new AgendaLlenaException("La agenda está llena.");
        }
        contactos[contador++] = c;
    }

    @Override
    public boolean existeContacto(Contacto c) {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].equals(c)) return true;
        }
        return false;
    }

    @Override
    public void listarContactos() {
        Contacto[] copia = Arrays.copyOf(contactos, contador);
        Arrays.sort(copia);
        for (Contacto c : copia) {
            System.out.println(c);
        }
    }

    @Override
    public Contacto buscarContacto(String nombre, String apellido) throws ContactoNoEncontradoException {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)
                    && contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                return contactos[i];
            }
        }
        throw new ContactoNoEncontradoException("Contacto no encontrado.");
    }

    @Override
    public void eliminarContacto(Contacto c) throws ContactoNoEncontradoException {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].equals(c)) {
                contactos[i] = contactos[--contador];
                contactos[contador] = null;
                return;
            }
        }
        throw new ContactoNoEncontradoException("No se puede eliminar, contacto no existe.");
    }

    @Override
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) throws ContactoNoEncontradoException {
        Contacto c = buscarContacto(nombre, apellido);
        c.setTelefono(nuevoTelefono);
    }

    @Override
    public boolean agendaLlena() {
        return contador >= contactos.length;
    }

    @Override
    public int espaciosLibres() {
        return contactos.length - contador;
    }
}