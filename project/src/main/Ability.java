package main;

public class Ability {
    private int dmg;
    private boolean stun;
    private boolean dodge;
    private int cd;
    private int initialCooldown;

    public Ability(int dmg, boolean stun, boolean dodge, int cd) {
        this.dmg = dmg;
        this.stun = stun;
        this.dodge = dodge;
        this.cd = cd;
        this.initialCooldown = cd;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getInitialCooldown() {
        return initialCooldown;
    }

    @Override
    public String toString() {
        return "\nAbility" +
                "\ndmg: " + dmg +
                "\nstun: " + ((stun == true) ? "Yes" : "No") +
                "\ndodge: " + ((dodge == true) ? "Yes" : "No") +
                "\ncd: " + cd +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if(!(o instanceof Ability)) return false;

        Ability a = (Ability) o;

        return Integer.compare(dmg, a.getDmg()) == 0
                && Boolean.compare(stun, a.isStun()) == 0
                && Boolean.compare(dodge, a.isDodge()) == 0
                && Integer.compare(initialCooldown, a.getInitialCooldown()) == 0;
    }
}
