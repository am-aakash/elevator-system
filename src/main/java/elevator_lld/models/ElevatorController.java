package elevator_lld.models;

import java.util.Collections;
import java.util.PriorityQueue;

public class ElevatorController {
  private Integer elevatorId;
  private PriorityQueue<Integer> downMinHeap = new PriorityQueue<>(Collections.reverseOrder());
  // 1, 3, 5 <----direction of elevator
  private PriorityQueue<Integer> upMaxHeap = new PriorityQueue<>();
  // 5, 2, 1 <----direction of elevator

  public ElevatorController(Elevator elevator) {
    this.elevatorId = elevator.getId();
  }

  public Integer getElevatorId() {
    return elevatorId;
  }

  public PriorityQueue<Integer> getDownMinHeap() {
    return downMinHeap;
  }

  public void addToDownMinHeap(Integer floor) {
    if (this.downMinHeap.contains(floor))
      return;
    else
      this.downMinHeap.add(floor);
  }

  public Integer popDownMinHeap() {
    return this.downMinHeap.poll();
  }

  public Integer getDownMin() {
    return this.downMinHeap.peek();
  }

  public PriorityQueue<Integer> getUpMaxHeap() {
    return upMaxHeap;
  }

  public void addToUpMaxHeap(Integer floor) {
    if (this.upMaxHeap.contains(floor))
      return;
    this.upMaxHeap.add(floor);
  }

  public Integer popUpMaxHeap() {
    return this.upMaxHeap.poll();
  }

  public Integer getUpMax() {
    return this.upMaxHeap.peek();
  }

  public void setDownMinHeap(PriorityQueue<Integer> downMinHeap) {
    this.downMinHeap = downMinHeap;
  }

  public void setUpMaxHeap(PriorityQueue<Integer> upMaxHeap) {
    this.upMaxHeap = upMaxHeap;
  }

  @Override
  public String toString() {
    return "ElevatorController{" +
        "elevatorId=" + elevatorId +
        ", downMinHeap=" + downMinHeap +
        ", upMaxHeap=" + upMaxHeap +
        '}';
  }
}
