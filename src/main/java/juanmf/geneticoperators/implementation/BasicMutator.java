/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juanmf.geneticoperators.implementation;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import juanmf.geneticoperators.Gen;
import juanmf.geneticoperators.Individual;
import juanmf.geneticoperators.IndividualFactory;
import juanmf.geneticoperators.Mutator;

/**
 *
 * @author juan.fernandez
 */
public class BasicMutator <I extends Individual, G extends Gen> implements Mutator<I>{
    /**
     * This numbers represents that in 10% of populations, there will be one mutant
     * for each thousand subjects.
     */
    private static final float POPULATOIN_MUTATION_PROBABILITY = 0.1f;
    private static final float INDIVIDUAL_MUTATION_PROBABILITY = 0.001f;
    private final IndividualFactory<I, G> individualFactory;
    
    private static final Random RANDOM = new Random();

    public BasicMutator(IndividualFactory<I, G> individualFactory) {
        this.individualFactory = individualFactory;
    }

    @Override
    public I mutate(I i) {
        // elegir un gen random y cambiarlo. 
        int genIdx = RANDOM.nextInt(i.size());
        System.out.println("Mutating at gen:" + genIdx + ". individual:" + i);
        return individualFactory.createIndividual(i, genIdx);
    }

    @Override
    public List<I> mutate(List<I> individuals) {
        if (POPULATOIN_MUTATION_PROBABILITY < RANDOM.nextFloat()) {
            return individuals;
        }
        for (int i = 0; i < individuals.size(); i++) {
            if (INDIVIDUAL_MUTATION_PROBABILITY < RANDOM.nextFloat()) {
                continue;
            }
            I subject = individuals.get(i);
            I mutant = mutate(subject);
            if (mutant != subject) {
                individuals.set(i, mutant);
            }
        }
        return individuals;
    }
}
