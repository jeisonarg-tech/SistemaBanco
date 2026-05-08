import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Clase genérica que implementa una cola FIFO (First In, First Out).
 * El primero en llegar es el primero en ser atendido.
 * Usa Queue<T> de Java internamente con LinkedList.
 */
public class ColaBanco<T> {

    // Cola interna usando LinkedList que implementa Queue
    private Queue<T> cola;

    // Constructor - inicializa la cola vacía
    public ColaBanco() {
        this.cola = new LinkedList<>();
    }

    /**
     * Encolar - agrega un elemento al FINAL de la cola (FIFO).
     * offer() es más seguro que add() porque no lanza excepción si falla.
     */
    public void encolar(T elemento) {
        cola.offer(elemento);
    }

    /**
     * Desencolar - saca y retorna el PRIMER elemento de la cola.
     * Lanza excepción si la cola está vacía.
     */
    public T desencolar() {
        if (isEmpty())
            throw new NoSuchElementException("No hay clientes en espera.");
        return cola.poll(); // poll() saca el primero y lo retorna
    }

    /**
     * Ver el próximo sin sacarlo de la cola.
     * peek() mira el primero sin modificar la cola.
     */
    public T verProximo() {
        if (isEmpty())
            throw new NoSuchElementException("No hay clientes en espera.");
        return cola.peek();
    }

    /**
     * Muestra todos los clientes en espera sin modificar la cola.
     * Recorre con for-each que no altera el orden.
     */
    public void mostrarCola() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        int turno = 1;
        for (T c : cola) {
            System.out.println("Turno " + turno++ + ": " + c);
        }
    }

    // Retorna cuántos clientes hay en espera
    public int tamanio() {
        return cola.size();
    }

    // Retorna true si no hay clientes en la cola
    public boolean isEmpty() {
        return cola.isEmpty();
    }

    // Vacía completamente la cola
    public void vaciar() {
        cola.clear();
    }

    // Para persistencia - acceso a la cola interna
    public Queue<T> getCola() {
        return cola;
    }

    public void setCola(Queue<T> cola) {
        this.cola = cola;
    }
}