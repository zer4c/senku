/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

/**
 *
 * @author brenda
 */
public class ModelTableroCruz extends ModelTablero {

    public ModelTableroCruz() {
        super(7, 7);
        construirTableroCruz();
    }

    private void construirTableroCruz() {
        
        // Activamos solo las posiciones que forman una cruz (como el Senku clásico)
        for (int i = 0; i < ifilas; i++) {
            for (int j = 0; j < icolumnas; j++) {
                // Activa si está en la cruz
                boolean enZonaCentral = (i >= 2 && i <= 4) || (j >= 2 && j <= 4);
                boolean noCentro = !(i == 3 && j == 3); // Centro vacío

                if (enZonaCentral && noCentro) {
                    fichas.get(i).get(j).vactivar();
                }
            }
        }
        fichas.get(3).get(3).veliminar();
    }

    @Override
    public boolean bfichaActiva(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha ficha = getFicha(ifila, icolumna);
        if (ficha.bestaActiva()) {
            bres = true;
        }
        return bres;
    }
    
    @Override
    public boolean beliminarFicha(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha ficha = getFicha(ifila, icolumna);
        if (ficha.bestaActiva()) {
            ficha.veliminar();
            bres = true;
        }
        return bres;
    }
    
    @Override
    public boolean bpuedeComer(int ifila, int icolumna, String sdireccion) {
        int ifilaMedio = ifila;
        int icolumnaMedio = icolumna;
        int ifilaDestino = ifila;
        int icolumnaDestino = icolumna;

        sdireccion = sdireccion.toUpperCase();
        switch (sdireccion) {
            case "ARRIBA" -> {
                ifilaMedio -= 1;
                ifilaDestino -= 2;
                break;
            }
            case "ABAJO" -> {
                ifilaMedio += 1;
                ifilaDestino += 2;
                break;
            }
            case "IZQUIERDA" -> {
                icolumnaMedio -= 1;
                icolumnaDestino -= 2;
                break;
            }
            case "DERECHA" -> {
                icolumnaMedio += 1;
                icolumnaDestino += 2;
                break;
            }
            default -> {
                return false;
            }
        }

        ModelFicha origen = getFicha(ifila, icolumna);
        ModelFicha medio = getFicha(ifilaMedio, icolumnaMedio);
        ModelFicha destino = getFicha(ifilaDestino, icolumnaDestino);

        boolean bpuedeComer = false;

        if (!origen.besInvisible() && !medio.besInvisible() && !destino.besInvisible()) {
            boolean origenValido = origen.bestaActiva();
            boolean medioOcupado = medio.bestaActiva();
            boolean destinoLibre = destino.bestaEliminada();

            bpuedeComer = origenValido && medioOcupado && destinoLibre;
        }
        return bpuedeComer;
    }

    @Override
    public boolean bmoverFicha(int ifilaOrigen, int icolOrigen, String sdireccion) {
        boolean bmovimientoValido = false;
        if (bpuedeComer(ifilaOrigen, icolOrigen, sdireccion)) {
            int ifilaMedio = ifilaOrigen, icolMedio = icolOrigen;
            int ifilaDestino = ifilaOrigen, icolDestino = icolOrigen;

            switch (sdireccion.toUpperCase()) {
                case "ARRIBA" -> {
                    ifilaMedio -= 1;
                    ifilaDestino -= 2;
                    break;
                }
                case "ABAJO" -> {
                    ifilaMedio += 1;
                    ifilaDestino += 2;
                    break;
                }
                case "IZQUIERDA" -> {
                    icolMedio -= 1;
                    icolDestino -= 2;
                    break;
                }
                case "DERECHA" -> {
                    icolMedio += 1;
                    icolDestino += 2;
                    break;
                }
                default -> ifilaDestino = -1;
            }

            ModelFicha origen = getFicha(ifilaOrigen, icolOrigen);
            ModelFicha medio = getFicha(ifilaMedio, icolMedio);
            ModelFicha destino = getFicha(ifilaDestino, icolDestino);

            if (!origen.besInvisible() && !medio.besInvisible() && !destino.besInvisible()) {
                origen.veliminar();
                medio.veliminar();
                destino.vactivar();
                bmovimientoValido = true;
            }
        }
        return bmovimientoValido;
    }
}