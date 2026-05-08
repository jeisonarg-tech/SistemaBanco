import java.io.*;
import java.time.LocalTime;

/**
 * Clase que guarda y carga la cola desde un archivo de texto.
 * Cada línea del archivo representa un cliente con sus datos separados por
 * comas.
 * Esto permite que la cola persista aunque el programa se cierre.
 */
public class Persistencia {

    // Nombre del archivo donde se guarda la cola
    private static final String ARCHIVO = "cola_banco.txt";

    /**
     * Guarda todos los clientes de la cola en el archivo.
     * Formato de cada línea: nombre,cedula,tipo,hora,prioridad
     */
    public static void guardar(ColaBanco<Cliente> colaBanco) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Cliente c : colaBanco.getCola()) {
                bw.write(c.getNombre() + "," +
                        c.getIdentificacion() + "," +
                        c.getTipoTransaccion() + "," +
                        c.getHoraLlegada() + "," +
                        c.isPrioridad());
                bw.newLine(); // salto de línea entre cada cliente
            }
            System.out.println("Cola guardada correctamente en " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Carga los clientes desde el archivo y los agrega a la cola.
     * Si el archivo no existe, simplemente no hace nada.
     */
    public static void cargar(ColaBanco<Cliente> colaBanco) {
        File archivo = new File(ARCHIVO);

        // Si no existe el archivo, no hay nada que cargar
        if (!archivo.exists()) {
            System.out.println("No se encontró archivo previo. Iniciando cola vacía.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            int cargados = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    Cliente c = new Cliente(
                            partes[0], // nombre
                            partes[1], // identificacion
                            partes[2], // tipoTransaccion
                            LocalTime.parse(partes[3]), // horaLlegada
                            Boolean.parseBoolean(partes[4]) // prioridad
                    );
                    colaBanco.encolar(c);
                    cargados++;
                }
            }
            System.out.println("Cola cargada: " + cargados + " cliente(s) recuperados.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}