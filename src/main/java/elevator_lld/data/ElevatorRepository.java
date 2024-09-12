package elevator_lld.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import elevator_lld.models.Building;
import elevator_lld.models.Elevator;
import elevator_lld.models.ElevatorController;

public class ElevatorRepository {
  // Use thread-safe collections
  private static final Map<Integer, Elevator> elevators = new ConcurrentHashMap<>();
  private static final Map<Integer, ElevatorController> elevatorControllers = new ConcurrentHashMap<>();
  private static final Map<Integer, Building> buildings = new ConcurrentHashMap<>();

  // Private constructor to prevent instantiation
  private ElevatorRepository() {
  }

  // Static inner helper class - Bill Pugh Singleton Design
  private static class ElevatorRepositoryHelper {
    // Singleton instance is created only when accessed for the first time
    private static final ElevatorRepository INSTANCE = new ElevatorRepository();
  }

  // Public method to provide access to the singleton instance
  public static ElevatorRepository getInstance() {
    return ElevatorRepositoryHelper.INSTANCE;
  }

  // Provide controlled access methods to the collections
  public Elevator getElevator(Integer id) {
    return elevators.get(id);
  }

  public Integer addElevator(Elevator elevator) {
    elevators.put(elevator.getId(), elevator);
    return elevator.getId();
  }

  public ElevatorController getElevatorController(Integer id) {
    return elevatorControllers.get(id);
  }

  public void addElevatorController(Integer id, ElevatorController controller) {
    elevatorControllers.put(id, controller);
  }

  public Building getBuilding(Integer id) {
    return buildings.get(id);
  }

  public Integer addBuilding(Building building) {
    buildings.put(building.getId(), building);
    return building.getId();
  }

  public void addElevatorToBuilding(Integer buildingId, Elevator elevator) {
    buildings.get(buildingId).addElevator(elevator.getId());
  }

}
