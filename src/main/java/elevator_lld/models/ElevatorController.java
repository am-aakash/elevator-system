package elevator_lld.models;

import java.util.Collections;
import java.util.PriorityQueue;

public class ElevatorController {
  PriorityQueue<Integer> downMaxHeap = new PriorityQueue<>();
  PriorityQueue<Integer> upMinHeap = new PriorityQueue<>(Collections.reverseOrder());
  private int elevatorId;

  public ElevatorController(int elevatorId) {
    this.elevatorId = elevatorId;
  }

  void submitExternalRequest(int floor) {
    
  }
}
