package agenda.modelo;

import agenda.exceptions.*;

public abstract class AgendaBase {
    protected Contacto[] contactos;
    protected int contador;

    public AgendaBase(int capacidad) {
        this.contactos = new Contacto[capacidad];
        this.contador = 0;
    }

    public abstract void anadirContacto(Contacto c) throws AgendaLlenaException, ContactoDuplicadoException, DatosInvalidosException;
    public abstract boolean existeContacto(Contacto c);
    public abstract void listarContactos();
    public abstract Contacto buscarContacto(String nombre, String apellido) throws ContactoNoEncontradoException;
    public abstract void eliminarContacto(Contacto c) throws ContactoNoEncontradoException;
    public abstract void modificarTelefono(String nombre, String apellido, String nuevoTelefono) throws ContactoNoEncontradoException;
    public abstract boolean agendaLlena();
    public abstract int espaciosLibres();
}
