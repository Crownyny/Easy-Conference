package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Evaluator;
import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories.EvaluatorRepository;
import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.EvaluatorDTO;

@Service
public class EvaluatorServiceImp implements IEvaluatorService {
    private EvaluatorRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public EvaluatorServiceImp(EvaluatorRepository servicioAccesoBaseDatos, ModelMapper mapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = mapper;
    }

    @Override
    public List<EvaluatorDTO> findAll() {
        System.out.println("Retornando todos los evaluadores");
        List<Evaluator> listEvaluators = servicioAccesoBaseDatos.getAllEvaluators();
        List<EvaluatorDTO> listEvaluatorsDTO = this.modelMapper.map(listEvaluators, new TypeToken<List<EvaluatorDTO>>() {
        }.getType());
        return listEvaluatorsDTO;
    }

    @Override
    public EvaluatorDTO findById(int id) {
        System.out.println("Buscando evaluador por id");
        Evaluator evaluator = servicioAccesoBaseDatos.getEvaluatorById(id);
        EvaluatorDTO evaluatorDTO = null;
        if(evaluator != null){
            evaluatorDTO = this.modelMapper.map(evaluator, EvaluatorDTO.class);
        }
        return evaluatorDTO;
    }

    @Override
    public EvaluatorDTO store(EvaluatorDTO evaluator) {
        System.out.println("Guardando evaluador");
        Evaluator evaluatorEntity = this.modelMapper.map(evaluator, Evaluator.class);
        Evaluator evaluatorStored = servicioAccesoBaseDatos.store(evaluatorEntity);
        EvaluatorDTO evaluatorDTO = this.modelMapper.map(evaluatorStored, EvaluatorDTO.class);
        return evaluatorDTO;
    }

    // @Override
    // public void store(EvaluatorDTO ... evaluators) {
    // System.out.println("Guardando lista de evaluadores");
    //  for(EvaluatorDTO evaluator : evaluators){
    //  Evaluator evaluatorEntity = this.modelMapper.map(evaluator, Evaluator.class);
    //   servicioAccesoBaseDatos.store(evaluatorEntity);
    //  }
    // }

    @Override
    public EvaluatorDTO update(int id, EvaluatorDTO evaluator) {
        System.out.println("Actualizando evaluador");
        Evaluator evaluatorEntity = this.modelMapper.map(evaluator, Evaluator.class);
        Evaluator evaluatorUpdated = servicioAccesoBaseDatos.update(evaluatorEntity);
        EvaluatorDTO evaluatorDTO = this.modelMapper.map(evaluatorUpdated, EvaluatorDTO.class);
        return evaluatorDTO;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("Eliminando evaluador");
        return servicioAccesoBaseDatos.delete(id);
    }
}
