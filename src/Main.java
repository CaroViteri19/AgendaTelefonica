import logica.Agenda;
import logica.Contacto;
public class Main {
    public static void main(String[] args) {
        // Creamos una agenda con máximo 3 contactos
        Agenda agenda = new Agenda(3);

        // Creamos contactos de prueba (mock)
        Contacto c1 = new Contacto("Miguel", "Ángel", "123456789");
        Contacto c2 = new Contacto("Lina", "Gómez", "987654321");
        Contacto c3 = new Contacto("Carlos", "Rojas", "111222333");
        Contacto c4 = new Contacto("Miguel", "Ángel", "555555555"); // Duplicado (mismo nombre+apellido)

        // Añadimos contactos
        agenda.añadirContacto(c1);
        agenda.añadirContacto(c2);
        agenda.añadirContacto(c3);
        agenda.añadirContacto(c4); // No debería añadirse por duplicado

        // Listamos contactos
        System.out.println("Lista de contactos:");
        agenda.listarContactos();

        // Buscar contacto
        agenda.buscaContacto("Lina", "Gómez");

        // Eliminar contacto
        agenda.eliminarContacto(c2);

        // Ver espacios libres
//        System.out.println("Espacios libres: " + agenda.espaciosLibres());

        // Agenda llena?
        System.out.println("¿Agenda llena?: " + agenda.agendaLlena());
    }
}

