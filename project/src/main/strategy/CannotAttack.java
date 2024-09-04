package main.strategy;

public class CannotAttack implements Attacks {
    public boolean attackEnemy(Pokemon pokemon, Pokemon enemy, boolean isAlly) {
        System.out.println("Can't attack");
        return false;
    }
}