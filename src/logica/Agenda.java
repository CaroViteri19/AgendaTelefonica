package logica;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Agenda {
    private List<Contacto> contactos;
    private int maxContactos;

    // Constructor con tamaño personalizado
    public Agenda(int maxContactos) {
        this.contactos = new ArrayList<>();
        this.maxContactos = maxContactos;
    }

    // Constructor con tamaño por defecto (10)
    public Agenda() {
        this(10);
    }

    // Verifica si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= maxContactos;
    }

    // Devuelve los espacios libres
    public int espacioLibre() {
        return maxContactos - contactos.size();
    }

    // Verifica si un contacto existe
    public boolean existeContacto(Contacto c) {
        return contactos.contains(c); // gracias al equals de Contacto
    }

    // Añadir contacto
    public String añadirContacto(Contacto c) {
        if (agendaLlena()) {
            return "No se puede añadir. La agenda está llena.";
        }
        if (existeContacto(c)) {
            return "No se puede añadir. El contacto ya existe.";
        }
        contactos.add(c);
        return "El contacto fue añadido.";
    }

    // Listar contactos
    public List<String> listarContactos() {
        List<String> lista = new ArrayList<>();

        if (contactos.isEmpty()) {
            lista.add("No hay contactos en la agenda.");
            return lista;
        }

        contactos.sort(Comparator
                .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
        );

        for (Contacto c : contactos) {
            lista.add(c.toString());
        }

        return lista;
    }

    // Buscar contacto por nombre y apellido
    public Contacto buscaContacto(String nombre, String apellido) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {
                return c;
            }
        }
        return null;
    }

    // Eliminar contacto
    public boolean eliminarContacto(Contacto c) {
        return contactos.remove(c); // usa equals
    }

    // Modificar teléfono
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {
                c.setTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }
}
