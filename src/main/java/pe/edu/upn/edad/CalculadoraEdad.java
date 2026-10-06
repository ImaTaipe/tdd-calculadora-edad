package pe.edu.upn.edad;

import java.time.LocalDate;

public class CalculadoraEdad {

    public static final String ERROR_FECHA_FUTURA = "La fecha de nacimiento no puede ser posterior a la fecha actual";

    public int calcularEdad(LocalDate fechaNacimiento, LocalDate fechaActual) {
        validarFechas(fechaNacimiento, fechaActual);

        int edad = fechaActual.getYear() - fechaNacimiento.getYear();
        if (!yaCumplioAnios(fechaNacimiento, fechaActual)) {
            edad--;
        }
        return edad;
    }
    

    private void validarFechas(LocalDate fechaNacimiento, LocalDate fechaActual) {
        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalArgumentException(ERROR_FECHA_FUTURA);
        }
    }
    

    private boolean yaCumplioAnios(LocalDate fechaNacimiento, LocalDate fechaActual) {
        if (fechaActual.getMonthValue() > fechaNacimiento.getMonthValue()) {
            return true;
        }
        if (fechaActual.getMonthValue() == fechaNacimiento.getMonthValue()) {
            return fechaActual.getDayOfMonth() >= fechaNacimiento.getDayOfMonth();
        }
        
        return false;
    }
    
}