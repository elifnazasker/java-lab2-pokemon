package moves;

import ru.ifmo.se.pokemon.*;

public final class Flamethrower extends SpecialMove {
    public Flamethrower() { super(Type.FIRE, 90, 1); }
    @Override protected void applyOppEffects(Pokemon target) {
        if (Math.random() < 0.10) Effect.burn(target);
    }
    @Override protected String describe() { return "uses Flamethrower"; }
}

