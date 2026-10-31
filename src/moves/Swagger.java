package moves;

import ru.ifmo.se.pokemon.*;
import support.MoveState;

public final class Swagger extends StatusMove {
    public Swagger() { super(Type.NORMAL, 0, 0.85); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        MoveState.beginAction(self);
        return super.checkAccuracy(self, target);
    }
    @Override protected void applyOppEffects(Pokemon target) {
        target.setMod(Stat.ATTACK, 2);
        Effect.confuse(target);
    }
    @Override protected String describe() { return "uses Swagger"; }
}
