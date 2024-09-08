package elevator_lld.data;

import java.util.*;
import elevator_lld.models.*;

public class ElevatorRepository {
  public static final Map<String, Elevator> elevators = new HashMap<>();
  public static final Map<String, ElevatorController> elevatorControllers = new HashMap<>();
  public static final List<Building> buildings = new ArrayList<>();
}
