package logica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Agenda con capacidad de 5 contactos (puedes cambiarlo o dejarlo por defecto)
        Agenda agenda = new Agenda(5);

        int opcion;
        do {
            System.out.println("\n===== MENU AGENDA =====");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Ver si agenda está llena");
            System.out.println("7. Ver espacios libres");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    try {
                        Contacto c = new Contacto(nombre, apellido, telefono);
                        agenda.añadirContacto(c);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido a buscar: ");
                    String apellido = sc.nextLine();

                    Contacto c = new Contacto(nombre, apellido, "0000000000"); // teléfono no importa
                    if (agenda.existeContacto(c)) {
                        System.out.println("El contacto existe.");
                    } else {
                        System.out.println("El contacto NO existe.");
                    }
                }

                case 3 -> {
                    System.out.print("Nombre a eliminar: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido a eliminar: ");
                    String apellido = sc.nextLine();

                    Contacto c = new Contacto(nombre, apellido, "0000000000");
                    agenda.eliminarContacto(c);
                }

                case 4 -> {
                    System.out.println("Lista de contactos:");
                    for (String fila : agenda.listarContactos()) {
                        System.out.println(fila);
                    }
                }

                case 5 -> {
                    System.out.print("Nombre del contacto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido del contacto: ");
                    String apellido = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String nuevoTel = sc.nextLine();

                    agenda.modificarTelefono(nombre, apellido, nuevoTel);
                }

                case 6 -> {
                    if (agenda.agendaLlena()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("Aún hay espacio.");
                    }
                }

                case 7 -> {
                    System.out.println("Espacios libres: " + agenda.espacioLibre());
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
