package OldschoolRuneScape;

import OldschoolRuneScape.TechnoSheepShearerTasks.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(
        name = "TechnoSheepShearer",
        description = "Technophite's Sheep Shearer Script",
        properties = "author=Technophite; topic=999; client=4;"
)

public class TechnoSheepShearer extends PollingScript<ClientContext> {
    public List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        Walker walker = new Walker(ctx);
        System.out.println("Adding Sheep Shearer task list.");
        taskList.add(new RunSwitcher(ctx));
        taskList.add(new ShearerQuestComplete(ctx));
        taskList.add(new ShearerSpeakToFredWithWool(ctx, walker));
        taskList.add(new ShearerWheelToFarmer(ctx, walker));
        taskList.add(new ShearerSpinWool(ctx, walker));
        taskList.add(new ShearerSheepPenToWheel(ctx, walker));
        taskList.add(new ShearerShearWool(ctx, walker));
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }
}
