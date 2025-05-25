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
        vconstruirTablero();
    }

    private void vconstruirTablero() {
        
        for (int i = 0; i < ifilas; i++) {
            for (int j = 0; j < icolumnas; j++) {
                boolean benZonaCentral = (i >= 2 && i <= 4) || (j >= 2 && j <= 4);
                boolean bnoCentro = !(i == 3 && j == 3); 

                if (benZonaCentral && bnoCentro) {
                    ALfichas.get(i).get(j).vactivar();
                }
            }
        }
        ALfichas.get(3).get(3).veliminar();
    }

    @Override
    public boolean bfichaActiva(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha MFficha = MFgetFicha(ifila, icolumna);
        if (MFficha.bestaActiva()) {
            bres = true;
        }
        return bres;
    }
    
    @Override
    public boolean beliminarFicha(int ifila, int icolumna) {
        boolean bres = false;
        ModelFicha MFficha = MFgetFicha(ifila, icolumna);
        if (MFficha.bestaActiva()) {
            MFficha.veliminar();
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

        ModelFicha MForigen = MFgetFicha(ifila, icolumna);
        ModelFicha MFmedio = MFgetFicha(ifilaMedio, icolumnaMedio);
        ModelFicha MFdestino = MFgetFicha(ifilaDestino, icolumnaDestino);

        boolean bpuedeComer = false;
        if(MForigen != null && MFmedio != null && MFdestino != null){
            if (!MForigen.besInvisible() && !MFmedio.besInvisible() && !MFdestino.besInvisible()) {
                boolean borigenValido = MForigen.bestaActiva();
                boolean bmedioOcupado = MFmedio.bestaActiva();
                boolean bdestinoLibre = MFdestino.bestaEliminada();

                bpuedeComer = borigenValido && bmedioOcupado && bdestinoLibre;
            }
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

            ModelFicha MForigen = MFgetFicha(ifilaOrigen, icolOrigen);
            ModelFicha MFmedio = MFgetFicha(ifilaMedio, icolMedio);
            ModelFicha MFdestino = MFgetFicha(ifilaDestino, icolDestino);

            if (!MForigen.besInvisible() && !MFmedio.besInvisible() && !MFdestino.besInvisible()) {
                MForigen.veliminar();
                MFmedio.veliminar();
                MFdestino.vactivar();
                bmovimientoValido = true;
            }
        }
        return bmovimientoValido;
    }
}