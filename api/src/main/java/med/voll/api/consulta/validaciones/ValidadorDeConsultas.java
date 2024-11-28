package med.voll.api.consulta.validaciones;

import med.voll.api.consulta.DTOCancelamientoConsulta;
import med.voll.api.consulta.DTOConsultaReserva;

public interface ValidadorDeConsultas {
    void validar(DTOConsultaReserva datos);
    void validar(DTOCancelamientoConsulta datos);
}
