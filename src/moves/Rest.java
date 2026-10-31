package moves;

import ru.ifmo.se.pokemon.*;

public final class Rest extends StatusMove {
    public Rest() { super(Type.PSYCHIC, 0, 1); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        return self.getCondition() != Status.SLEEP && self.getHP() < self.getStat(Stat.HP);
    }
    @Override protected void applySelfEffects(Pokemon self) {
        int missing = (int) Math.round(self.getStat(Stat.HP) - self.getHP());
        self.setMod(Stat.HP, -missing);
        // This engine ticks the condition at the END of the current turn.
        // Three ticks = current turn plus two following turns without actions.
        self.setCondition(new Effect().condition(Status.SLEEP).attack(0).turns(3));
    }
    @Override protected String describe() { return "uses Rest"; }
}
