package moves;

import ru.ifmo.se.pokemon.*;

public final class DreamEater extends SpecialMove {
    private double drained;
    public DreamEater() { super(Type.PSYCHIC, 100, 1); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        drained = 0;
        return target.getCondition() == Status.SLEEP && super.checkAccuracy(self, target);
    }
    @Override protected void applyOppDamage(Pokemon target, double damage) {
        double remaining = Math.max(0, target.getHP());
        int applied = (int) Math.min(Math.round(damage), Math.ceil(remaining));
        drained = Math.min(applied, remaining);
        super.applyOppDamage(target, applied);
    }
    @Override protected void applySelfDamage(Pokemon self, double damage) {
        int missing = (int) Math.round(self.getStat(Stat.HP) - self.getHP());
        int heal = Math.min(missing, drained == 0 ? 0 : Math.max(1, (int) Math.round(drained / 2.0)));
        if (heal > 0) self.setMod(Stat.HP, -heal);
    }
    @Override protected String describe() { return "uses Dream Eater"; }
}
