package main.items;

public class Weapon extends Item{

    public Weapon(String name, int attack, int specialAttack) {
        super(name, 0, attack, specialAttack, 0, 0);
    }

    @Override
    public String toString() {
        return
                "\nname: " + super.getName() + " (Weapon)" +
                "\nattack: " + ((super.getAttack() == 0) ? "-" : super.getAttack()) +
                "\nspecialAttack: " + ((super.getSpecialAttack() == 0) ? "-" : super.getSpecialAttack()) +
                '\n';
    }
}
