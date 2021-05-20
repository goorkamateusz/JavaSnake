public class Gameplay extends GameBase {

    @Override
    protected void awake() {
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        super.awake();
    }
}
