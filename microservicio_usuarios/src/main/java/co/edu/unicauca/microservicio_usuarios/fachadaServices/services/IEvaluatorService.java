package co.edu.unicauca.microservicio_usuarios.fachadaServices.services;

import co.edu.unicauca.microservicio_usuarios.fachadaServices.DTO.EvaluatorDTO;
import java.util.List;

public interface IEvaluatorService {
    /**
     * Metodo para obtener todos los evaluadores
     * @return Lista de evaluadores
     */
    public List<EvaluatorDTO> findAll();

    /**
     * Metodo para buscar un evaluador por su identificador
     * @param id Identificador del evaluador
     * @return Evaluador
     */
    public EvaluatorDTO findById(int id);

    /**
     * Metodo para guardar un evaluador
     * @param evaluator Evaluador a guardar
     * @return Evaluador ingresado
     */
    public EvaluatorDTO store(EvaluatorDTO evaluator);

    /**
     * Metodo para guardar una lista de evaluadores
     * @param evaluators Lista de evaluadores
     * @return Evaluadores ingresados
     */
    // public void store(EvaluatorDTO ... evaluators);

    /**
     * Metodo para actualizar un evaluador
     * @param id Identificador del evaluador
     * @param evaluator Evaluador a actualizar
     * @return Evaluador actualizado
     */
    public EvaluatorDTO update(int id, EvaluatorDTO evaluator);

    /**
     * Metodo para eliminar un evaluador
     * @param id Identificador del evaluador
     * @return Evaluador eliminado
     */
    public boolean delete(int id);
}
