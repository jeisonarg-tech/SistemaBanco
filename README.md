# SistemaBanco - Cola FIFO para Atención en Ventanilla

## Descripción
Sistema de gestión de turnos para una ventanilla de banco desarrollado en Java.
Implementa una estructura de datos FIFO (First In, First Out) donde el primer
cliente en llegar es el primero en ser atendido.

## Autor
Jeison - Uniremington - Algoritmos II - 3er Semestre

## Estructura del proyecto
SistemaBanco/
├── Cliente.java        → Clase que representa un cliente con sus datos
├── ColaBanco.java      → Implementación genérica de la cola FIFO
├── MenuPrincipal.java  → Menú interactivo en consola (punto de entrada)
├── Persistencia.java   → Guarda y carga la cola desde archivo de texto
└── cola_banco.txt      → Archivo generado automáticamente al guardar

## Requisitos
- Java 8 o superior
- No requiere librerías externas

## Cómo compilar y ejecutar

Compilar:
javac *.java

Ejecutar:
java MenuPrincipal

## Opciones del menú
| Opción | Descripción |
|--------|-------------|
| 1 | Agregar cliente (tomar turno) |
| 2 | Atender siguiente cliente |
| 3 | Ver próximo cliente en espera |
| 4 | Mostrar todos los clientes en cola |
| 5 | Consultar cantidad de clientes en espera |
| 6 | Vaciar la cola |
| 7 | Salir y guardar |

## ¿Cómo funciona la FIFO?
La cola FIFO funciona como una fila de banco real:
- El primer cliente en llegar es el primero en ser atendido
- Los nuevos clientes siempre se agregan al FINAL de la cola
- Solo se puede atender al cliente del FRENTE de la cola

## Persistencia
Al salir con la opción 7 el sistema guarda automáticamente todos los
clientes en espera en el archivo cola_banco.txt. Al volver a ejecutar
el programa los clientes se recuperan automáticamente.

## Diagrama de clases UML
<img width="900" height="574" alt="diagrama_uml" src="https://github.com/user-attachments/assets/62317a3f-adff-40ac-b87d-e39cc2d3ee9c" />

