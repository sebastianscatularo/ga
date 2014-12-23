package ar.com.sebastianscatularo.ga.operators;

import ar.com.sebastianscatularo.ga.population.Individual;
import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com
 */
interface Operator {
    void apply(Collection<? extends Individual> population);
}
