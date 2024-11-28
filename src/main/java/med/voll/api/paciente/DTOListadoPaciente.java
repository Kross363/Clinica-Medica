package med.voll.api.paciente;

public record DTOListadoPaciente(Long id, String nombre, String email, String dni) {

    public DTOListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDni());
    }
}
