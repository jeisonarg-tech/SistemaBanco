import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase principal que muestra el menú interactivo del sistema.
 * Es el punto de entrada del programa (contiene el main).
 * Solo se comunica con ColaBanco y Persistencia, nunca toca la cola
 * directamente.
 */
public class MenuPrincipal {

    // Cola global del banco y lector de teclado
    static ColaBanco<Cliente> colaBanco = new ColaBanco<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Al iniciar, cargar la cola guardada anteriormente
        Persistencia.cargar(colaBanco);

        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                // Si el usuario escribe letras en lugar de números
                System.out.println("Opción inválida. Ingrese un número del 1 al 7.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    atenderCliente();
                    break;
                case 3:
                    verProximo();
                    break;
                case 4:
                    mostrarCola();
                    break;
                case 5:
                    consultarCantidad();
                    break;
                case 6:
                    vaciarCola();
                    break;
                case 7:
                    // Guardar la cola antes de salir
                    Persistencia.guardar(colaBanco);
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Elija entre 1 y 7.");
            }
        } while (opcion != 7);

        sc.close();
    }

    // Imprime las opciones del menú en consola
    static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE COLA DEL BANCO ===");
        System.out.println("1. Agregar cliente (tomar turno)");
        System.out.println("2. Atender siguiente cliente");
        System.out.println("3. Ver próximo cliente en espera");
        System.out.println("4. Mostrar todos los clientes en cola");
        System.out.println("5. Consultar cantidad de clientes en espera");
        System.out.println("6. Vaciar la cola");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Opción 1 - pide los datos del cliente y lo agrega a la cola
    static void agregarCliente() {
        try {
            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine().trim();

            System.out.print("Ingrese identificación (cédula): ");
            String id = sc.nextLine().trim();

            System.out.print("Tipo de transacción (Depósito/Retiro/Consulta/Pago): ");
            String tipo = sc.nextLine().trim();

            System.out.print("Hora de llegada (HH:MM): ");
            LocalTime hora = LocalTime.parse(sc.nextLine().trim());

            System.out.print("¿Tiene prioridad? (adulto mayor/discapacitado) s/n: ");
            boolean prioridad = sc.nextLine().trim().equalsIgnoreCase("s");

            // Crear el cliente y agregarlo a la cola
            Cliente c = new Cliente(nombre, id, tipo, hora, prioridad);
            colaBanco.encolar(c);
            System.out.println("Cliente agregado. Turno número: " + colaBanco.tamanio());

        } catch (IllegalArgumentException e) {
            // Error de validación en los datos del cliente
            System.out.println("Error en los datos: " + e.getMessage());
        } catch (DateTimeParseException e) {
            // Error si la hora no tiene el formato correcto
            System.out.println("Error: formato de hora inválido. Use HH:MM (ej: 10:30).");
        }
    }

    // Opción 2 - atiende y elimina el primer cliente de la cola
    static void atenderCliente() {
        try {
            Cliente c = colaBanco.desencolar();
            System.out.println("Atendiendo a: " + c);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Opción 3 - muestra el próximo cliente sin sacarlo de la cola
    static void verProximo() {
        try {
            System.out.println("Próximo cliente: " + colaBanco.verProximo());
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Opción 4 - muestra todos los clientes en espera
    static void mostrarCola() {
        System.out.println("\n=== CLIENTES EN ESPERA ===");
        colaBanco.mostrarCola();
    }

    // Opción 5 - muestra cuántos clientes hay esperando
    static void consultarCantidad() {
        System.out.println("Clientes en espera: " + colaBanco.tamanio());
    }

    // Opción 6 - vacía toda la cola
    static void vaciarCola() {
        colaBanco.vaciar();
        System.out.println("Cola vaciada correctamente.");
    }
}