package moves;

import ru.ifmo.se.pokemon.*;
import support.MoveState;

/** Assignment spelling retained; modern English name is Vise Grip. */
public final class ViceGrip extends PhysicalMove {
    public ViceGrip() { super(Type.NORMAL, 55, 1); }
    @Override protected boolean checkAccuracy(Pokemon self, Pokemon target) {
        MoveState.beginAction(self);
        return super.checkAccuracy(self, target);
    }
    @Override protected String describe() { return "uses Vice Grip"; }
}
