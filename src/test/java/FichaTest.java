/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import grupo8.senku.model.ModelFicha;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author pablo
 */
public class FichaTest {
    ModelFicha ficha;
    @BeforeEach
    public void iniciarCondiciones(){
        ficha = new ModelFicha();
    }
    @Test
    public void testVerificarActivarEstadoFicha(){
        ficha.vactivar();
        assertTrue(ficha.bestaActiva());
    }
    @Test
    public void testVerificarFichaEliminada(){
        ficha.veliminar();
        assertTrue(ficha.bestaEliminada());
    }
    @Test
    public void testVerificarFichaInvisible(){
        ficha.vhacerInvisible();
        assertTrue(ficha.besInvisible());
    }
    @Test
    public void testVerificarCambiosEstado(){
        ficha.vactivar();
        ficha.veliminar();
        assertFalse(ficha.bestaActiva());
    }
}
