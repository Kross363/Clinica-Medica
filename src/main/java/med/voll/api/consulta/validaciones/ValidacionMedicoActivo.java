package med.voll.api.consulta.validaciones;

import med.voll.api.consulta.DTOCancelamientoConsulta;
import med.voll.api.consulta.DTOConsultaReserva;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DTOConsultaReserva datos){
        if(datos.idMedico()==null){
            return;
        }
        var medicoActivo=medicoRepository.findActivoById(datos.idPaciente());
        if(!medicoActivo){
            throw new ValidacionException("No se puede reservar la consulta poque el medico no esta activo");
        }
    }

    @Override
    public void validar(DTOCancelamientoConsulta datos) {

    }
}
