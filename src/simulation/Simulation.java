package simulation;

import simulation.island.Island;
import simulation.utils.Settings;
import simulation.utils.StatisticPrinter;
import simulation.utils.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Simulation {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ExecutorService fixedTP = Executors.newFixedThreadPool(8);
    private int dayCounter;

    public Simulation(Island island) {
        this.island = island;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(
                this::dayTick,
                Settings.DAY_DURATION,
                Settings.DAY_DURATION,
                TimeUnit.MILLISECONDS
        );
    }

    public void dayTick() {
        dayCounter++;
        List<Callable<Void>> tasks = new ArrayList<>();
        tasks.add(() -> {
            island.growPlants(Settings.MAX_DAY_GROWING_COUNT);
            return null;
        });
        tasks.add(() -> {
            island.eatingTick();
            return null;
        });
        tasks.add(() -> {
            island.reproducingTick();
            return null;
        });
        tasks.add(() -> {
            island.moveTick();
            return null;
        });
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (dayCounter % Settings.PRINTING_INTERVAL == 0) {
            System.out.println("========== DAY " + dayCounter + " ==========");
            StatisticPrinter printer = new StatisticPrinter();
            printer.printFullStatistics(Statistics.getInstance());
        }
    }
}
