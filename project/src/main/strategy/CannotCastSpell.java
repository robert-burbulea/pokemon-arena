package main.strategy;

import main.Ability;

public class CannotCastSpell implements CastSpell {
    public boolean castSpell(Pokemon pokemon, Pokemon enemy, Ability ability) {
        System.out.println("Can't cast spell");
        return false;
    }
}