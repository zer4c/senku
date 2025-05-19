package grupo8.senku.test;

import grupo8.senku.model.ModelTablero;
import grupo8.senku.model.ModelTableroCruz;
import grupo8.senku.model.ModelTableroTriangular;

public class TestSenku {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS TABLERO TRIANGULAR ===");
        probarTableroTriangular();
        
        System.out.println("\n=== PRUEBAS TABLERO CRUZ ===");
        probarTableroCruz();
    }

    private static void probarTableroTriangular() {
        ModelTablero triangular = new ModelTableroTriangular();
        imprimirTablero(triangular);

        // Prueba 1: Movimiento diagonal válido (2,2) sobre (1,3) -> (0,4)
        System.out.println("\n[Triangular] Prueba 1: Movimiento (2,2) diagonal sobre (1,3) -> (0,4)");
        System.out.println("¿Puede comer? " + triangular.bpuedeComer(2, 2, "DIAGONAL_IZQUIERDA"));
        System.out.println("Movimiento realizado? " + triangular.bmoverFicha(2, 2, "DIAGONAL_IZQUIERDA"));
        imprimirTablero(triangular);

        // Prueba 2: Movimiento inválido desde (0,4) (debería fallar)
        System.out.println("\n[Triangular] Prueba 2: Movimiento inválido desde (0,4)");
        System.out.println("¿Puede comer? " + triangular.bpuedeComer(0, 4, "DIAGONAL_DERECHA"));
        System.out.println("Movimiento realizado? " + triangular.bmoverFicha(0, 4, "DIAGONAL_DERECHA"));
        imprimirTablero(triangular);
    }

    private static void probarTableroCruz() {
        ModelTablero cruz = new ModelTableroCruz();
        imprimirTablero(cruz);

        // Prueba 1: Movimiento horizontal válido (3,5) sobre (3,4) -> (3,3)
        System.out.println("\n[Cruz] Prueba 1: Movimiento (3,5) sobre (3,4) -> (3,3)");
        System.out.println("¿Puede comer? " + cruz.bpuedeComer(3, 5, "IZQUIERDA"));
        System.out.println("Movimiento realizado? " + cruz.bmoverFicha(3, 5, "IZQUIERDA"));
        imprimirTablero(cruz);

        // Prueba 2: Movimiento inválido desde (3,3) (debería fallar)
        System.out.println("\n[Cruz] Prueba 2: Movimiento inválido desde (3,3)");
        System.out.println("¿Puede comer? " + cruz.bpuedeComer(3, 3, "DERECHA"));
        System.out.println("Movimiento realizado? " + cruz.bmoverFicha(3, 3, "DERECHA"));
        imprimirTablero(cruz);

        // Prueba 3: Movimiento vertical válido (5,3) sobre (4,3) -> (3,3)
        System.out.println("\n[Cruz] Prueba 3: Movimiento (5,3) sobre (4,3) -> (3,3)");
        System.out.println("¿Puede comer? " + cruz.bpuedeComer(5, 3, "ARRIBA"));
        System.out.println("Movimiento realizado? " + cruz.bmoverFicha(5, 3, "ARRIBA"));
        imprimirTablero(cruz);
    }

    private static void imprimirTablero(ModelTablero tablero) {
        System.out.println("\nEstado actual del tablero:");
        for (int i = 0; i < tablero.igetFilas(); i++) {
            for (int j = 0; j < tablero.igetColumnas(); j++) {
                if (tablero.getFicha(i, j) != null && tablero.getFicha(i, j).bestaVisible()) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}