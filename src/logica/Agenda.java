package logica;

import java.util.List;
import java.util.ArrayList;

public class Agenda {

    /// atributos
    private ArrayList<Contacto> contactos;
    private int maxContactos;


    /// constructores
    public Agenda(int maxContactos){
        contactos = new ArrayList<>();
        this.maxContactos = maxContactos;
    }


    public Agenda(){
        contactos = new ArrayList<>();
        this.maxContactos = 10;
    }

    /// metodos
    public boolean agendaLlena(){
        return contactos.size() >= maxContactos;
    }


    public int espacioLibre(){
        return maxContactos - contactos.size();
    }

    public boolean existeContacto(Contacto c){
        for (Contacto contacto : contactos){
            if (contacto.equals(c)){
                return true;
            }
        }
        return false;
    }

    public String añadirContacto(Contacto c){
        if (agendaLlena()){
            return "No se puede añadir. La agenda está llena";
        }
        if (existeContacto(c)){
            return "No se puede añadir. El contacto ya existe";

        }
        contactos.add(c);
        return "el contacto fue añadido";
    }


    public List<String> listarContactos() {
        List<String> lista = new ArrayList<>();

        if (contactos.isEmpty()) {
            lista.add("No hay contactos en la agenda.");
            return lista;
        }

        contactos.sort((c1, c2) -> {
            int compNombre = c1.getNombre().compareToIgnoreCase(c2.getNombre());
            if (compNombre != 0) return compNombre;
            return c1.getApellido().compareToIgnoreCase(c2.getApellido());
        });

        for (Contacto c : contactos) {
            lista.add(c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono());
        }
        return lista;
    }


    public Contacto buscaContacto(String nombre, String apellido) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {
                return c;
            }
        }
        return null;
    }


    public boolean eliminarContacto(Contacto c) {
        return contactos.remove(c);
    }


    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {
//                c.setTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }









}
