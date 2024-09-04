package main.items;

public class Miscellaneous extends Item{
    public Miscellaneous(String name, int HP, int attack, int specialAttack, int defense, int specialDefense) {
        super(name, HP, attack, specialAttack, defense, specialDefense);
    }

    //will only show bonuses
    @Override
    public String toString() {
        return
                "\nname: " + super.getName() + " (Miscellaneous)" +
                ((super.getHP() == 0) ? "" : "\nHP: " + super.getHP()) +
                ((super.getAttack() == 0) ? "" : "\nattack: " + super.getAttack()) +
                ((super.getSpecialAttack() == 0) ? "" : "\nspecialAttack: " + super.getSpecialAttack()) +
                ((super.getDefense() == 0) ? "" : "\ndefense: " + super.getDefense()) +
                ((super.getSpecialAttack() == 0) ? "" : "\nspecialDefense: " + super.getSpecialDefense()) +
                '\n';
    }
}
