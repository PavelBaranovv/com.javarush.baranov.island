package simulation;

import simulation.island.Island;
import simulation.utils.Settings;
import simulation.utils.statistics.IslandRenderer;
import simulation.utils.statistics.StatisticPrinter;
import simulation.utils.statistics.Statistics;

import java.util.Map;
import java.util.concurrent.*;

public class Simulation {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final StatisticPrinter printer = new StatisticPrinter();
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

        island.growPlantsRandom(Settings.MIN_DAY_GROWING_COUNT, Settings.MAX_DAY_GROWING_COUNT);
        island.reproducingTick();
        island.eatingTick();
        island.moveTick();
        island.hungerTick();
        island.cleanDeadEntities();

        if (dayCounter % Settings.PRINTING_INTERVAL == 0) {
            System.out.println("========== DAY " + dayCounter + " ==========");
            printer.printFullStatistics(Statistics.getInstance(), island);
        }

        checkForEnd();
    }

    private void checkForEnd() {
        if (Statistics.getInstance().getAnimalsCount() <= Settings.MIN_ANIMALS_COUNT) {
            System.out.println("Животных почти не осталось");
            stopSimulation();
        } else if (Statistics.getInstance().getHerbivoresCount() <= Settings.MIN_HERBIVOROUS_COUNT) {
            System.out.println("Хищники победили. Травоядных почти не осталось");
            stopSimulation();
        } else if (Statistics.getInstance().getPredatorsCount() <= Settings.MIN_PREDATORS_COUNT) {
            System.out.println("Травоядные победили. Хищников почти не осталось");
            stopSimulation();
        } else if (dayCounter >= Settings.MAX_SIMULATION_DURATION) {
            System.out.printf("Симуляция продлилась более %d дней\n", Settings.MAX_SIMULATION_DURATION);
            System.out.println("Для продолжения увеличьте параметр MAX_SIMULATION_DURATION");
            stopSimulation();
        } else {
            Map<String, Integer> extinctAnimals = Statistics.getInstance().getExtinctAnimals();
            if (extinctAnimals.size() >= Settings.MAX_EXTINCT_ANIMALS) {
                System.out.printf("Вымерло %d животных: ", extinctAnimals.size());
                extinctAnimals.forEach((emoji, count) -> System.out.print(emoji));
                System.out.println();
                System.out.println("Для продолжения увеличьте параметр MAX_EXTINCT_ANIMALS");
                stopSimulation();
            }

        }
    }

    private void stopSimulation() {
        System.out.println("Симуляция завершена");
        scheduler.shutdown();

        System.out.println();
        System.out.println("Остров:");
        System.out.println("(показана заполненность каждой локации растениями и животными,");
        System.out.println("стикер обозначает вид, которого в локации больше всего)");
        IslandRenderer renderer = new IslandRenderer(island);
        renderer.drawIsland();
    }
}
