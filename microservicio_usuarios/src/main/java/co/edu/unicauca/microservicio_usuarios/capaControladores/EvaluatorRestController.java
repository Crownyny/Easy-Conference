package co.edu.unicauca.microservicio_usuarios.capaControladores;

import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.services.IEvaluatorService;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.EvaluatorDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class EvaluatorRestController {
    @Autowired
    private IEvaluatorService evaluatorService;

    @GetMapping("/evaluators")
    public List<EvaluatorDTO> getAllEvaluators() {
        List<EvaluatorDTO> listEvaluators = evaluatorService.findAll();
        return listEvaluators;
    }

    @GetMapping("/evaluators/{id}")
    public EvaluatorDTO getEvaluatorById(@PathVariable int id) {
        EvaluatorDTO evaluator = evaluatorService.findById(id);
        return evaluator;
    }

    @PostMapping("/evaluators")
    public EvaluatorDTO saveEvaluator(@RequestBody EvaluatorDTO evaluator) {
        EvaluatorDTO evaluatorStored = evaluatorService.store(evaluator);
        return evaluatorStored;
    }

    //@PostMapping("/evaluators2")
    //public void saveEvaluators(@RequestBody EvaluatorDTO ... evaluators) {
        // evaluatorService.store(evaluators);
    //}

    @PutMapping("/evaluators/{id}")
    public EvaluatorDTO updateEvaluator(@PathVariable int id, @RequestBody EvaluatorDTO evaluator) {
        EvaluatorDTO evaluatorUpdated = evaluatorService.update(id, evaluator);
        return evaluatorUpdated;
    }

    @DeleteMapping("/evaluators/{id}")
    public boolean deleteEvaluator(@PathVariable int id) {
        return evaluatorService.delete(id);
    }

}
