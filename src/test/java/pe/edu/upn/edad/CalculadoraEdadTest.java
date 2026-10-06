package pe.edu.upn.edad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas TDD - Calculadora de Edad")
class CalculadoraEdadTest {

    private CalculadoraEdad calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraEdad();
    }

    @Test
    @DisplayName("Ciclo 1: Cumpleaños ya pasó este año")
    void testEdadCuandoYaCumplioAniosEsteAnio() {
        LocalDate nacimiento = LocalDate.of(2000, 5, 15);
        LocalDate hoy = LocalDate.of(2026, 9, 30);
        assertEquals(26, calculadora.calcularEdad(nacimiento, hoy));
    }

    @Test
    @DisplayName("Ciclo 2: Cumpleaños aún no llega este año")
    void testEdadCuandoAunNoCumpleAniosEsteAnio() {
        LocalDate nacimiento = LocalDate.of(2000, 12, 10);
        LocalDate hoy = LocalDate.of(2026, 9, 30);
        assertEquals(25, calculadora.calcularEdad(nacimiento, hoy));
    }

    @Test
    @DisplayName("Ciclo 2: Mismo día del cumpleaños")
    void testEdadElDiaDelCumpleanios() {
        LocalDate nacimiento = LocalDate.of(2000, 9, 30);
        LocalDate hoy = LocalDate.of(2026, 9, 30);
        assertEquals(26, calculadora.calcularEdad(nacimiento, hoy));
    }

    @Test
    @DisplayName("Ciclo 3: Fecha futura lanza IllegalArgumentException")
    void testFechaNacimientoFuturaLanzaExcepcion() {
        LocalDate nacimiento = LocalDate.of(2027, 1, 1);
        LocalDate hoy = LocalDate.of(2026, 9, 30);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularEdad(nacimiento, hoy);
        });
        assertEquals(CalculadoraEdad.ERROR_FECHA_FUTURA, ex.getMessage());
    }

    @Test
    @DisplayName("Ciclo 4: Nacido en 29 de feb, evaluado en 28 de feb en año no bisiesto")
    void testBisiestoUnDiaAntesDelCumpleanios() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate hoy = LocalDate.of(2025, 2, 28);
        assertEquals(20, calculadora.calcularEdad(nacimiento, hoy));
    }

    @Test
    @DisplayName("Ciclo 4: Nacido en 29 de feb, evaluado en 1 de marzo en año no bisiesto")
    void testBisiestoCumpleEl1DeMarzo() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate hoy = LocalDate.of(2025, 3, 1);
        assertEquals(21, calculadora.calcularEdad(nacimiento, hoy));
    }

    @Test
    @DisplayName("Ciclo 4: Nacido en 29 de feb, evaluado en 29 de feb en año bisiesto")
    void testBisiestoEnAnioBisiesto() {
        LocalDate nacimiento = LocalDate.of(2004, 2, 29);
        LocalDate hoy = LocalDate.of(2028, 2, 29);
        assertEquals(24, calculadora.calcularEdad(nacimiento, hoy));
    }
}