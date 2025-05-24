/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

/**
 *
 * @author brenda
 */
public class ModelTableroTriangular extends ModelTablero {

    public ModelTableroTriangular() {
        super(5, 9); // 5 filas y 9 columnas para formar el triángulo equilátero
        construirTableroTriangular();
    }

    private void construirTableroTriangular() {
   
        for (int fila = 0; fila < ifilas; fila++) {
            int fichasEnFila = fila + 1;
            int inicio = (icolumnas - 1) / 2 - fila;
            for (int i = 0; i < fichasEnFila; i++) {
                int col = inicio + i * 2;
                if (col >= 0 && col < icolumnas) {
                    // Activamos todas las fichas excepto el vértice superior
                    if (!(fila == 0 && col == 4)) {
                        fichas.get(fila).get(col).vactivar();
                    }
                }
            }
        }
    // Aseguramos explícitamente que el vértice esté vacío
        fichas.get(0).get(4).veliminar();
}

    @Override
    public boolean bfichaActiva(int ifila, int icolumna) {
        ModelFicha ficha = getFicha(ifila, icolumna);
        return ficha.bestaActiva();
    }

    @Override
    public boolean beliminarFicha(int ifila, int icolumna) {
        ModelFicha ficha = getFicha(ifila, icolumna);
        if (ficha.bestaActiva()) {
            ficha.veliminar();
            return true;
        }
        return false;
    }

    @Override
    public boolean bpuedeComer(int ifila, int icolumna, String sdireccion) {
        int df = 0;
        int dc = 0;
        
        switch (sdireccion.toUpperCase()) {
            case "ARRIBA_IZQUIERDA" -> { df = -1; dc = -1; }
            case "ARRIBA_DERECHA"   -> { df = -1; dc = +1; }
            case "IZQUIERDA"        -> { df = 0; dc = -2; }
            case "DERECHA"          -> { df = 0; dc = +2; }
            case "ABAJO_IZQUIERDA"  -> { df = +1; dc = -1; }
            case "ABAJO_DERECHA"    -> { df = +1; dc = +1; }
            default -> {
                return false;
            }
        }

        int fm = ifila + df;
        int cm = icolumna + dc;
        int fd = ifila + 2 * df;
        int cd = icolumna + 2 * dc;

        boolean puedeComer = false;

        ModelFicha origen = getFicha(ifila, icolumna);
        ModelFicha medio = getFicha(fm, cm);
        ModelFicha destino = getFicha(fd, cd);
        if(origen != null && medio != null && destino != null){
            if (!origen.besInvisible() && !medio.besInvisible() && !destino.besInvisible()) {
                if (origen.bestaActiva() && medio.bestaActiva() && destino.bestaEliminada()) {
                    puedeComer = true;
                }
            }
        }
        return puedeComer;
    }

    @Override
    public boolean bmoverFicha(int ifila, int icolumna, String sdireccion) {
        boolean movimientoRealizado = false;

        if (bpuedeComer(ifila, icolumna, sdireccion)) {
            int df = 0;
            int dc = 0;

            switch (sdireccion.toUpperCase()) {
                case "ARRIBA_IZQUIERDA" -> { df = -1; dc = -1; }
                case "ARRIBA_DERECHA"   -> { df = -1; dc = +1; }
                case "IZQUIERDA"        -> { df = 0; dc = -2; }
                case "DERECHA"          -> { df = 0; dc = +2; }
                case "ABAJO_IZQUIERDA"  -> { df = +1; dc = -1; }
                case "ABAJO_DERECHA"    -> { df = +1; dc = +1; }
                default -> {
                    return false;
                }
            }

            int fm = ifila + df;
            int cm = icolumna + dc;
            int fd = ifila + 2 * df;
            int cd = icolumna + 2 * dc;

            ModelFicha origen = getFicha(ifila, icolumna);
            ModelFicha medio = getFicha(fm, cm);
            ModelFicha destino = getFicha(fd, cd);

            if (!origen.besInvisible() && !medio.besInvisible() && !destino.besInvisible()) {
                origen.veliminar();
                medio.veliminar();
                destino.vactivar();
                movimientoRealizado = true;
            }
        }

        return movimientoRealizado;
    }
}