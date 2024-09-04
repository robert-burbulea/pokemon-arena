package main.strategy;

public class CanAttack implements Attacks {
    public boolean attackEnemy (Pokemon pokemon, Pokemon enemy, boolean isAlly) {
        int damage;
        if (enemy.isDodge()) {
            damage = 0;
            enemy.setDodge(false);
            System.out.println("Your enemy dodged the attack!");
        } else if (pokemon.getNormalAttack() > 0) {
            damage = pokemon.getNormalAttack() - enemy.getDef();
            if (enemy.getDef() > pokemon.getNormalAttack()) {
                damage = 0;
                System.out.println("Enemy's defense is too high");
            }
        } else {
            damage = pokemon.getSpecialAttack() - enemy.getSpecialDefense();
            if (enemy.getSpecialDefense() > pokemon.getSpecialAttack()) {
                damage = 0;
                System.out.println("Enemy's defense is too high");
            }
        }

        enemy.setHP(enemy.getHP() - damage);

        if (isAlly) {
            System.out.print("Damage done: " + damage);
        } else {
            System.out.println("Damage received: " + damage);
        }
        System.out.println();

        return true;
    }
}