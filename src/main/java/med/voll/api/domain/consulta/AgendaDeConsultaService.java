package med.voll.api.domain.consulta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.pacienteRepository;

@Service
public class AgendaDeConsultaService {
	
	@Autowired
	private pacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void agendar(DatosAgendarConsulta datos ) {
		
		var paciente = pacienteRepository.findById(datos.idPaciente()).get();
		var medico = medicoRepository.findById(datos.idMedico()).get();
		var consulta = new Consulta(null, new Medico(), new Paciente(),datos.fecha() );
		
		
		consultaRepository.save(consulta);
	}
}
