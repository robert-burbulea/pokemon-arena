package main.strategy;

import main.Ability;
import main.builder.Trainer;
import main.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private String name;
    private int HP;
    private int normalAttack;
    private int specialAttack;
    private int def;
    private int specialDefense;
    private Ability ability1;
    private Ability ability2;
    private boolean dodge;
    private boolean stunned;
    private static HashMap<String, Pokemon> allPokemons;

    public Attacks attackStatus;
    public CastSpell castSpellStatus;

    public Pokemon(String name, int HP, int normalAttack, int specialAttack, int def, int specialDefense, Ability ability1, Ability ability2) {
        this.name = name;
        this.HP = HP;
        this.normalAttack = normalAttack;
        this.specialAttack = specialAttack;
        this.def = def;
        this.specialDefense = specialDefense;
        this.ability1 = ability1;
        this.ability2 = ability2;

        this.attackStatus = new CanAttack();
        this.castSpellStatus = new CannotCastSpell();
    }

    public static Pokemon readPokemon(String line) {
        String[] words = line.split(" ");
        String name = words[0];
        int HP = Integer.parseInt(words[1]);
        int normalAttack = (words[2].equals("N/A")) ? 0 : Integer.parseInt(words[2]);
        int specialAttack = (words[3].equals("N/A")) ? 0 : Integer.parseInt(words[3]);
        int def = Integer.parseInt(words[4]);
        int specialDefense = Integer.parseInt(words[5]);

        CastSpell castSpell = null;

        Ability ability1 = null, ability2 = null;
        int dmg1, dmg2, cd1, cd2;
        boolean stun1, stun2, dodge1, dodge2;
        if (words[6].equals("N/A")) {
            ability1 = null;
            ability2 = null;
            castSpell = new CannotCastSpell();
        } else {
            dmg1 = Integer.parseInt(words[6]);
            stun1 = (words[7].equals("Yes") ? true : false);
            dodge1 = (words[8].equals("Yes") ? true : false);
            cd1 = Integer.parseInt(words[9]);
            ability1 = new Ability(dmg1, stun1, dodge1, cd1);

            if (!words[10].equals("N/A")) {
                dmg2 = Integer.parseInt(words[10]);
                stun2 = (words[11].equals("Yes") ? true : false);
                dodge2 = (words[12].equals("Yes") ? true : false);
                cd2 = Integer.parseInt(words[13]);
                ability2 = new Ability(dmg2, stun2, dodge2, cd2);
            }
            castSpell = new CanCastSpell();
        }
        Pokemon newPokemon = new Pokemon(name, HP, normalAttack, specialAttack, def, specialDefense, ability1, ability2);
        newPokemon.setCastSpell(castSpell);
        return newPokemon;
    }

    public void equipItem (Trainer trainer, Item item) {
        // problem: you can equip items that the trainer doesn't have!
        if (item == null) {
            System.out.println("Trainer " + trainer.getName() + " doesn't have " + item.getName() + "!");
        } else {
            this.HP += item.getHP();
            // if specialAttack or normalAttack is N/A, item doesn't have effect
            this.normalAttack = (this.normalAttack == 0) ? 0 : this.normalAttack + item.getAttack();
            this.specialAttack = (this.specialAttack == 0) ? 0 : this.specialAttack + item.getSpecialAttack();
            this.def += item.getDefense();
            this.specialDefense += item.getSpecialDefense();
        }
    }

    public void reduceCooldownsBy1() {
        if (this.ability1 != null)
            if (this.ability1.getCd() > 0) {
                this.ability1.setCd(this.ability1.getCd() - 1);
            }
        if (this.ability2 != null)
            if (this.ability2.getCd() > 0) {
            this.ability2.setCd((this.ability2.getCd() - 1));
        }
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

    public int getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(int normalAttack) {
        this.normalAttack = normalAttack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public static Map<String, Pokemon> getAllPokemons() {
        return allPokemons;
    }

    public static void setAllPokemons(HashMap<String, Pokemon> allPokemons) {
        Pokemon.allPokemons = allPokemons;
    }

    public Attacks getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(Attacks attackStatus) {
        this.attackStatus = attackStatus;
    }

    public CastSpell getCastSpellStatus() {
        return castSpellStatus;
    }

    public void setCastSpell(CastSpell castSpellStatus) {
        this.castSpellStatus = castSpellStatus;
    }

    @Override
    public String toString() {
        return "\nPokemon" +
                "\nname: " + name +
                "\nHP: " + HP +
                "\nnormalAttack: " + ((normalAttack == 0) ? "N/A" : normalAttack) +
                "\nspecialAttack: " + ((specialAttack == 0) ? "N/A" : specialAttack) +
                "\ndef: " + def +
                "\nspecialDefense: " + specialDefense +
                "\nability1:" + ((ability1 == null) ? "N/A" : ability1) +
                "ability2:" + ((ability2 == null) ? "N/A" : ability2) +
                '\n';
    }

    public String inGameToString() {
        return
                "\nname: " + name +
                "\nHP: " + HP +
                ((normalAttack == 0) ? "" : "\nnormalAttack: " + normalAttack) +
                ((specialAttack == 0) ? "" : "\nspecialAttack: " + specialAttack) +
                "\ndef: " + def +
                "\nspecialDefense: " + specialDefense +
                ((ability1 == null) ? "" : "\nability1:" + ability1) +
                ((ability2 == null) ? "" : "ability2:" +  ability2) +
                '\n';
    }

    // prints both pokemons side by side
    public String sideBySideToString(Pokemon enemy) {
        String s = "You" + " ".repeat(30 - "You".length()) + "Enemy";
        String[] words1 = this.inGameToString().split("\n");
        String[] words2 = enemy.inGameToString().split("\n");
        for (int i = 0; i < words1.length; i++) {
            s =  s + "\n" + words1[i] + " ".repeat(30 - words1[i].length()) + words2[i];
        }
        return s;
    }

}
