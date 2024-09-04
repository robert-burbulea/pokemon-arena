package main.items;

public class ItemFactory {
    public ItemFactory() {
    }

    public Item makeItem(String line) {
        String[] words = line.split(" ");

        String name = words[0];
        // if the name of the item has more words, add all the words to the name
        int index = 1;
        while (true) {
            if ( words[index].equals("-") || words[index].substring(0,1).equals("+") ) {
                break;
            }
            name = name + " " + words[index];
            index++;
        }
        // because all numbers start with "+", we'll need to apply substring
        int HP = (words[index].equals("-")) ? 0 : Integer.parseInt(words[index].substring(1));
        index++;
        int attack = (words[index].equals("-")) ? 0 : Integer.parseInt(words[index].substring(1));
        index++;
        int specialAttack = (words[index].equals("-")) ? 0 : Integer.parseInt(words[index].substring(1));
        index++;
        int defense = (words[index].equals("-")) ? 0 : Integer.parseInt(words[index].substring(1));
        index++;
        int specialDefense = (words[index].equals("-")) ? 0 : Integer.parseInt(words[index].substring(1));

        boolean damageBonuses = (attack > 0) || (specialAttack > 0);
        boolean armorBonuses = (HP > 0) || (defense > 0) || (specialDefense > 0);

        if (damageBonuses && !armorBonuses) {
            return new Weapon(name, attack, specialAttack);
        } else if (!damageBonuses && armorBonuses) {
            return new Armor(name, HP, defense, specialDefense);
        } else if (damageBonuses || armorBonuses){
            return new Miscellaneous(name, HP, attack, specialAttack, defense, specialDefense);
        } else return null;


    }
}
