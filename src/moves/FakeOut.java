package moves;

import ru.ifmo.se.pokemon.*;
import support.MoveState;
import support.LibraryAccess;

public final class FakeOut extends PhysicalMove {
    public FakeOut() { super(Type.NORMAL, 40, 1, 3, 1); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        boolean first = MoveState.beginAction(self);
        return first && super.checkAccuracy(self, target);
    }
    @Override protected void applyOppEffects(Pokemon target) {
        // Both moves are prepared before either attack; suppress the already
        // prepared move now, rather than adding a one-turn effect too late.
        LibraryAccess.cancelPreparedMove(target);
    }
    @Override protected String describe() { return "uses Fake Out"; }
}
