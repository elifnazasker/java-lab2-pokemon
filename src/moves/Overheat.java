package moves;

import ru.ifmo.se.pokemon.*;

public final class Overheat extends SpecialMove {
    public Overheat() { super(Type.FIRE, 130, 0.9); }
    @Override protected void applySelfEffects(Pokemon self) {
        self.setMod(Stat.SPECIAL_ATTACK, -2);
    }
    @Override protected String describe() { return "uses Overheat"; }
}
