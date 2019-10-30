package OldschoolRuneScape;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class RunSwitcher extends Task {

    private int nextEnergy = Random.nextInt(35, 55);

    public RunSwitcher(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.movement.running() && ctx.movement.energyLevel() >= nextEnergy;
    }

    @Override
    public void execute() {
        if (ctx.movement.running(true)) {
            if (Condition.wait(() -> ctx.movement.running(), 250, 5)) {
                this.nextEnergy = 30 + Random.nextInt(5, 25);
            }
        }
    }
}
