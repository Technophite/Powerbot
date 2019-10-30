package OldschoolRuneScape;

import OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant.*;
import OldschoolRuneScape.TechnoQuestingTasks.RomeoAndJuliet.RomeoLumbToCadavaBush;
import OldschoolRuneScape.TechnoQuestingTasks.SheepShearer.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(
        name = "TechnoQuesting",
        description = "Technophite's Questing Script",
        properties = "author=Technophite; topic=999; client=4;"
)

public class TechnoQuesting extends PollingScript<ClientContext> {

   public List<Task> taskList = new ArrayList<Task>();
   public Walker walker;

    @Override
    public void start() {
        this.walker = new Walker(ctx);

        String userOptions[] = {"Cook's Assistant", "Romeo and Juliet", "Sheep Shearer"};
        String userChoice = "" + (String) JOptionPane.showInputDialog(null, "Quest?", "TechnoQuesting", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);

        if (userChoice.equals("Cook's Assistant")) {
            System.out.println("Adding Cook's Assistant task list.");
            taskList.add(new RunSwitcher(ctx));
            taskList.add(new CooksAssistantComplete(ctx));
            taskList.add(new CooksAssistantFinishQuest(ctx, this.walker));
            taskList.add(new CooksAssistantEggToCook(ctx, this.walker));
            taskList.add(new CooksAssistantDairyCowToEgg(ctx, this.walker));
            taskList.add(new CooksAssistantMilkDairyCow(ctx, this.walker));
            taskList.add(new CooksAssistantWindmillToDairyCow(ctx, this.walker));
            taskList.add(new CooksAssistantFillPot(ctx, this.walker));
            taskList.add(new CooksAssistantMillFlour(ctx, this.walker));
            taskList.add(new CooksAssistantWheatToMillHopper(ctx, this.walker));
            taskList.add(new CooksAssistantPickWheat(ctx, this.walker));
            taskList.add(new CooksAssistantLumbToWheat(ctx, this.walker));
        } else if (userChoice.equals("Romeo and Juliet")) {
            System.out.println("This doesn't work yet");
            taskList.add(new RunSwitcher(ctx));
            taskList.add(new RomeoLumbToCadavaBush(ctx, this.walker));
        } else if (userChoice.equals("Sheep Shearer")) {
            System.out.println("Adding Sheep Shearer task list.");
            taskList.add(new RunSwitcher(ctx));
            taskList.add(new SheepShearerQuestComplete(ctx));
            taskList.add(new SheepShearerSpeakToFredWithWool(ctx, this.walker));
            taskList.add(new SheepShearerWheelToFarmer(ctx, this.walker));
            taskList.add(new SheepShearerSpinWool(ctx, this.walker));
            taskList.add(new SheepShearerSheepPenToWheel(ctx, this.walker));
            taskList.add(new SheepShearerShearWool(ctx, this.walker));
        } else {
            ctx.controller.stop();
        }
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
