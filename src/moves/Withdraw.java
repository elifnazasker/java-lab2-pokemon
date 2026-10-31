package moves;

import ru.ifmo.se.pokemon.*;
import support.MoveState;

public final class Withdraw extends StatusMove {
    public Withdraw() { super(Type.WATER, 0, 1); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        MoveState.beginAction(self);
        return true;
    }
    @Override protected void applySelfEffects(Pokemon self) { self.setMod(Stat.DEFENSE, 1); }
    @Override protected String describe() { return "uses Withdraw"; }
}
