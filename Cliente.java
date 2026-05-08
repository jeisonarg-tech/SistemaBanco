import java.time.LocalTime;

/**
 * Clase que representa un cliente en la cola del banco.
 * Contiene todos los datos del cliente y validaciones básicas.
 */
public class Cliente {

    // Atributos privados - encapsulamiento POO
    private String nombre;
    private String identificacion;
    private String tipoTransaccion;
    private LocalTime horaLlegada;
    private boolean prioridad; // true si es adulto mayor o discapacitado

    /**
     * Constructor con validaciones.
     * Lanza IllegalArgumentException si los datos son inválidos.
     */
    public Cliente(String nombre, String identificacion,
            String tipoTransaccion, LocalTime horaLlegada,
            boolean prioridad) {

        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");

        // Validar que la cédula tenga solo dígitos y entre 6 y 12 caracteres
        if (identificacion == null || !identificacion.matches("\\d{6,12}"))
            throw new IllegalArgumentException("Cédula inválida (solo dígitos, 6-12).");

        // Validar que el tipo de transacción sea uno de los permitidos
        if (!tipoTransaccion.matches("Depósito|Retiro|Consulta|Pago"))
            throw new IllegalArgumentException("Tipo inválido. Use: Depósito, Retiro, Consulta, Pago.");

        this.nombre = nombre.trim();
        this.identificacion = identificacion;
        this.tipoTransaccion = tipoTransaccion;
        this.horaLlegada = horaLlegada;
        this.prioridad = prioridad;
    }

    // Getters - permiten leer los atributos desde otras clases
    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    // Setters - permiten modificar los atributos desde otras clases
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Representación en texto del cliente.
     * Se usa al imprimir el objeto directamente.
     */
    @Override
    public String toString() {
        return "Cliente{nombre='" + nombre +
                "', id=" + identificacion +
                ", transacción=" + tipoTransaccion +
                ", hora=" + horaLlegada +
                ", prioridad=" + (prioridad ? "Sí" : "No") + "}";
    }
}