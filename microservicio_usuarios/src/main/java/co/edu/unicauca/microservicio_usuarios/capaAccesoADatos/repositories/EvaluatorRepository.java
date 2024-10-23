package co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.repositories;

import java.util.ArrayList;
import co.edu.unicauca.microservicio_usuarios.capaAccesoADatos.models.Evaluator;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluatorRepository {
    private ArrayList<Evaluator> listEvaluators;

    public EvaluatorRepository() {
        this.listEvaluators = new ArrayList<>();
    }

    public ArrayList<Evaluator> getAllEvaluators() {
        return listEvaluators;
    }

    public Evaluator getEvaluatorById(int id) {
        Evaluator evaluator = null;
        for (Evaluator e : listEvaluators) {
            if (e.getId() == id) {
                evaluator = e;
                break;
            }
        }
        return evaluator;
    }

    public Evaluator store(Evaluator evaluator) {
        Evaluator evaluador = null;
        if(listEvaluators.add(evaluator)) {
            evaluador = evaluator;
        }
        return evaluador;
    }

    /*public void store(Evaluator ... evaluators) {
        for(Evaluator evaluator : evaluators){
            listEvaluators.add(evaluator);
        }
    }*/

    public Evaluator update(Evaluator evaluator) {
        Evaluator evaluador = null;
        for (Evaluator e : listEvaluators) {
            if (e.getId() == evaluator.getId()) {
                e.setFirstName(evaluator.getFirstName());
                e.setLastName(evaluator.getLastName());
                e.setMail(evaluator.getMail());
                e.setAfiliation(evaluator.getAfiliation());
                evaluador = e;
                break;
            }
        }
        return evaluador;
    }

    public boolean delete(int id) {
        boolean result = false;
        for (Evaluator e : listEvaluators) {
            if (e.getId() == id) {
                listEvaluators.remove(e);
                result = true;
                break;
            }
        }
        return result;
    }
}
