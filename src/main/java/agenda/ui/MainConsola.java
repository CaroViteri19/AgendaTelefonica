package agenda.ui;


import agenda.exceptions.*;
import agenda.modelo.*;

import java.util.Scanner;

public class MainConsola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda(5); // tamaño fijo para pruebas

        int opcion;
        do {
            System.out.println("\n--- Menú Agenda ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Espacios libres");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String a = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String t = sc.nextLine();
                        agenda.anadirContacto(new Contacto(n, a, t));
                    }
                    case 2 -> agenda.listarContactos();
                    case 3 -> {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String a = sc.nextLine();
                        System.out.println("Teléfono: " + agenda.buscarContacto(n, a).getTelefono());
                    }
                    case 4 -> {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String a = sc.nextLine();
                        agenda.eliminarContacto(new Contacto(n, a, ""));
                    }
                    case 5 -> {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String a = sc.nextLine();
                        System.out.print("Nuevo teléfono: ");
                        String t = sc.nextLine();
                        agenda.modificarTelefono(n, a, t);
                    }
                    case 6 -> System.out.println("Espacios libres: " + agenda.espaciosLibres());
                }
            } catch (Exception e) {
                System.out.println("⚠ " + e.getMessage());
            }
        } while (opcion != 7);
    }
}
