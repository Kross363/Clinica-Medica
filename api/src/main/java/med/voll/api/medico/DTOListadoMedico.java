package med.voll.api.medico;

public record DTOListadoMedico(Long id,
                               String nombre,
                               String especialidad,
                               String dni,
                               String email) {
    //CONSTRUCTOR CREADO PARA METODO mostrarMedicos DE MedicoController
    public DTOListadoMedico(Medico medico){
        this(medico.getId(), medico.getNombre(),medico.getEspecialidad().toString(),medico.getDni(),medico.getEmail());
    }
}
