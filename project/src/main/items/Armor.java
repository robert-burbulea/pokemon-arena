package main.items;

public class Armor extends Item {

    public Armor(String name, int HP, int defense, int specialDefense) {
        super(name, HP, 0, 0, defense, specialDefense);
    }

    @Override
    public String toString() {
        return
                "\nname: " + super.getName() + " (Armor)" +
                "\nHP: " + ((super.getHP() == 0) ? "-" : super.getHP()) +
                "\ndefense: " + ((super.getDefense() == 0) ? "-" : super.getDefense()) +
                "\nspecialDefense: " + ((super.getSpecialDefense() == 0) ? "-" : super.getSpecialDefense()) +
                '\n';
    }
}
