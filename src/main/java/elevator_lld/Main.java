package elevator_lld;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import elevator_lld.models.Building;
import elevator_lld.models.Elevator;
import elevator_lld.models.Floor;
import elevator_lld.models.InternalButtons;
import elevator_lld.models.State;
import elevator_lld.services.ElevatorService;

public class Main {
    public static void main(String[] args) {
        ElevatorService elevatorService = new ElevatorService();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(elevatorService::moveElevatorsCron, 0, 2, TimeUnit.SECONDS);

        // running code

        Building building1 = new Building(List.of(new Floor(-2, List.of(State.UP))), null);
        for (int i = -1; i < 100; i++) {
            building1.addFloor(new Floor(i, List.of(State.UP, State.DOWN)));
        }
        building1.addFloor(new Floor(100, List.of(State.DOWN)));

        for (int i = 1; i <= 5; i++) {
            Set<Integer> floors = IntStream.rangeClosed(-2, 100 - (i - 1) * 10).boxed().collect(Collectors.toSet());
            elevatorService.addElevator(building1.getId(), new Elevator(State.IDLE, 0, new InternalButtons(floors)));
        }
    }
}