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

        // cron job
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            try {
                elevatorService.moveElevatorsCron();
            } catch (Exception e) {
                System.err.println("Error executing cron task: " + e.getMessage());
            }
        }, 1, 1, TimeUnit.SECONDS);

        // running code
        Building building1 = new Building(List.of(new Floor(-2, List.of(State.UP))), null);
        elevatorService.addBuilding(building1);
        for (int i = -1; i < 100; i++) {
            building1.addFloor(new Floor(i, List.of(State.UP, State.DOWN)));
        }
        building1.addFloor(new Floor(100, List.of(State.DOWN)));

        for (int i = 1; i <= 5; i++) {
            Set<Integer> floors = IntStream.rangeClosed(-2, 100 - (i - 1) * 10).boxed().collect(Collectors.toSet());
            elevatorService.addElevator(building1.getId(), new Elevator(State.IDLE, 0, new InternalButtons(floors)));
        }

        elevatorService.callElevator(building1.getId(), building1.getFloors().get(10), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(89), State.DOWN);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(4), State.UP);
        try {
            Thread.sleep(3000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(67), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(7), State.DOWN);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(86), State.UP);
        try {
            Thread.sleep(2000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(45), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(56), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(89), State.DOWN);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(8), State.UP);
        try {
            Thread.sleep(4000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(23), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(34), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(34), State.DOWN);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(65), State.UP);
        try {
            Thread.sleep(2000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(33), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(9), State.DOWN);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(5), State.UP);
        try {
            Thread.sleep(2000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(4), State.UP);
        elevatorService.callElevator(building1.getId(), building1.getFloors().get(1), State.UP);

        // do operations using Thread.sleep()
        try {
            Thread.sleep(1000);
            elevatorService.callElevator(building1.getId(),
                    building1.getFloors().get(10), State.DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
            elevatorService.callElevator(building1.getId(), building1.getFloors().get(18), State.UP);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}