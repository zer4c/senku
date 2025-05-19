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
        // Primero desactivamos todas las fichas
        for (int i = 0; i < ifilas; i++) {
            for (int j = 0; j < icolumnas; j++) {
                fichas.get(i).get(j).vsetVisible(false);
            }
        }

        // Activamos solo las posiciones que forman una cruz (como el Senku clásico)
        for (int i = 0; i < ifilas; i++) {
            for (int j = 0; j < icolumnas; j++) {
                // Activa si está en la cruz
                boolean enZonaCentral = (i >= 2 && i <= 4) || (j >= 2 && j <= 4);
                boolean noCentro = !(i == 3 && j == 3); // Centro vacío

                if (enZonaCentral && noCentro) {
                    fichas.get(i).get(j).vsetVisible(true);
                }
            }
        }
    }

    
    // Los demás métodos (bactivarFicha, beliminarFicha, bpuedeComer, bmoverFicha)
    // pueden permanecer igual que en tu implementación original
    @Override
    public boolean bactivarFicha(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha ficha = getFicha(ifila, icolumna);
        if (ficha != null && ficha.bestaVisible()) {
            bres = true;
        }
        return bres;
    }
    
    @Override
    public boolean beliminarFicha(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha ficha = getFicha(ifila, icolumna);
        if (ficha != null && ficha.bestaVisible()) {
            ficha.vsetVisible(false);
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
            }
            case "ABAJO" -> {
                ifilaMedio += 1;
                ifilaDestino += 2;
            }
            case "IZQUIERDA" -> {
                icolumnaMedio -= 1;
                icolumnaDestino -= 2;
            }
            case "DERECHA" -> {
                icolumnaMedio += 1;
                icolumnaDestino += 2;
            }
            default -> {
                return false;
            }
        }

        ModelFicha origen = getFicha(ifila, icolumna);
        ModelFicha medio = getFicha(ifilaMedio, icolumnaMedio);
        ModelFicha destino = getFicha(ifilaDestino, icolumnaDestino);

        boolean bpuedeComer = false;

        if (origen != null && medio != null && destino != null) {
            boolean origenValido = origen.bestaVisible();
            boolean medioOcupado = medio.bestaVisible();
            boolean destinoLibre = !destino.bestaVisible();

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
                }
                case "ABAJO" -> {
                    ifilaMedio += 1;
                    ifilaDestino += 2;
                }
                case "IZQUIERDA" -> {
                    icolMedio -= 1;
                    icolDestino -= 2;
                }
                case "DERECHA" -> {
                    icolMedio += 1;
                    icolDestino += 2;
                }
                default -> ifilaDestino = -1;
            }

            ModelFicha origen = getFicha(ifilaOrigen, icolOrigen);
            ModelFicha medio = getFicha(ifilaMedio, icolMedio);
            ModelFicha destino = getFicha(ifilaDestino, icolDestino);

            if (origen != null && medio != null && destino != null) {
                origen.vsetVisible(false);
                medio.vsetVisible(false);
                destino.vsetVisible(true);
                bmovimientoValido = true;
            }
        }
        return bmovimientoValido;
    }
}