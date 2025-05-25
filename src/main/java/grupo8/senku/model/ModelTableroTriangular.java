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
   
        for (int ifila = 0; ifila < ifilas; ifila++) {
            int ifichasEnFila = ifila + 1;
            int iinicio = (icolumnas - 1) / 2 - ifila;
            for (int i = 0; i < ifichasEnFila; i++) {
                int icol = iinicio + i * 2;
                if (icol >= 0 && icol < icolumnas) {
                    // Activamos tcolodas las ALfichas excepto el vértice superior
                    if (!(ifila == 0 && icol == 4)) {
                        ALfichas.get(ifila).get(icol).vactivar();
                    }
                }
            }
        }
    // Aseguramos explícitamente que el vértice esté vacío
        ALfichas.get(0).get(4).veliminar();
}

    @Override
    public boolean bfichaActiva(int ifila, int icolumna) {
        ModelFicha MFficha = MFgetFicha(ifila, icolumna);
        return MFficha.bestaActiva();
    }

    @Override
    public boolean beliminarFicha(int ifila, int icolumna) {
        ModelFicha MFficha = MFgetFicha(ifila, icolumna);
        if (MFficha.bestaActiva()) {
            MFficha.veliminar();
            return true;
        }
        return false;
    }

    @Override
    public boolean bpuedeComer(int ifila, int icolumna, String sdireccion) {
        int idf = 0;
        int idc = 0;
        
        switch (sdireccion.toUpperCase()) {
            case "ARRIBA_IZQUIERDA" -> { idf = -1; idc = -1; }
            case "ARRIBA_DERECHA"   -> { idf = -1; idc = +1; }
            case "IZQUIERDA"        -> { idf = 0; idc = -2; }
            case "DERECHA"          -> { idf = 0; idc = +2; }
            case "ABAJO_IZQUIERDA"  -> { idf = +1; idc = -1; }
            case "ABAJO_DERECHA"    -> { idf = +1; idc = +1; }
            default -> {
                return false;
            }
        }

        int ifm = ifila + idf;
        int icm = icolumna + idc;
        int ifd = ifila + 2 * idf;
        int icd = icolumna + 2 * idc;

        boolean bpuedeComer = false;

        ModelFicha MForigen = MFgetFicha(ifila, icolumna);
        ModelFicha MFmedio = MFgetFicha(ifm, icm);
        ModelFicha MFdestino = MFgetFicha(ifd, icd);
        if(MForigen != null && MFmedio != null && MFdestino != null){
            if (!MForigen.besInvisible() && !MFmedio.besInvisible() && !MFdestino.besInvisible()) {
                if (MForigen.bestaActiva() && MFmedio.bestaActiva() && MFdestino.bestaEliminada()) {
                    bpuedeComer = true;
                }
            }
        }
        return bpuedeComer;
    }

    @Override
    public boolean bmoverFicha(int ifila, int icolumna, String sdireccion) {
        boolean bmovimientoRealizado = false;

        if (bpuedeComer(ifila, icolumna, sdireccion)) {
            int idf = 0;
            int idc = 0;

            switch (sdireccion.toUpperCase()) {
                case "ARRIBA_IZQUIERDA" -> { idf = -1; idc = -1; }
                case "ARRIBA_DERECHA"   -> { idf = -1; idc = +1; }
                case "IZQUIERDA"        -> { idf = 0; idc = -2; }
                case "DERECHA"          -> { idf = 0; idc = +2; }
                case "ABAJO_IZQUIERDA"  -> { idf = +1; idc = -1; }
                case "ABAJO_DERECHA"    -> { idf = +1; idc = +1; }
                default -> {
                    return false;
                }
            }

            int ifm = ifila + idf;
            int icm = icolumna + idc;
            int ifd = ifila + 2 * idf;
            int icd = icolumna + 2 * idc;

            ModelFicha MForigen = MFgetFicha(ifila, icolumna);
            ModelFicha MFmedio = MFgetFicha(ifm, icm);
            ModelFicha MFdestino = MFgetFicha(ifd, icd);

            if (!MForigen.besInvisible() && !MFmedio.besInvisible() && !MFdestino.besInvisible()) {
                MForigen.veliminar();
                MFmedio.veliminar();
                MFdestino.vactivar();
                bmovimientoRealizado = true;
            }
        }

        return bmovimientoRealizado;
    }
}