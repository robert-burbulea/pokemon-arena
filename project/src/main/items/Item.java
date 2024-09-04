package main.items;

import java.util.Map;

public abstract class Item {
    private String name;
    private int HP;
    private int attack;
    private int specialAttack;
    private int defense;
    private int specialDefense;
    private static Map<String, Item> allItems;

    public Item(String name, int HP, int attack, int specialAttack, int defense, int specialDefense) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public static Map<String, Item> getAllItems() {
        return allItems;
    }

    public static void setAllItems(Map<String, Item> allItems) {
        Item.allItems = allItems;
    }

    @Override
    public String toString() {
        return "\nItem" +
                "\nname: " + name +
                "\nHP: " + ((HP == 0) ? "-" : HP) +
                "\nattack: " + ((attack == 0) ? "-" : attack) +
                "\nspecialAttack: " + ((specialAttack == 0) ? "-" : specialAttack) +
                "\ndefense: " + ((defense == 0) ? "-" : defense) +
                "\nspecialDefense: " + ((specialDefense == 0) ? "-" : specialDefense) +
                '\n';
    }

    public void displayItem() {
        System.out.println(this.toString());
    }
}
