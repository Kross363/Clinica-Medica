package med.voll.api.consulta;

import med.voll.api.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private List<ValidadorDeConsultas> validadores;
    public DTODetalleConsulta reservar(DTOConsultaReserva datos){
        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe paciente con el id informado");
        }
        if(datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe medico con el id informado");
        }
        validadores.forEach(v-> v.validar(datos));
        var medico=elegirMedico(datos);
        if(medico==null){
            throw new ValidacionException("No existe un medico disponible ese horario");
        }
        var paciente=pacienteRepository.findById(datos.idPaciente()).get();
        var consulta=new Consulta(null,medico,paciente,datos.fecha(),null);
        consultaRepository.save(consulta);
        return new DTODetalleConsulta(consulta);
    }

    private Medico elegirMedico(DTOConsultaReserva datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null)
        {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un medico");
        }
        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(),datos.fecha());
    }
    public void cancelar(DTOCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        validadores.forEach(v-> v.validar(datos));
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
