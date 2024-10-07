package elevator_lld.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotifyService {
  public static void notify(String message, String logLevel) { // logLevel - info, debug, warn, error
    // audio
    System.out.println(LocalDateTime.now() + " " + logLevel.toUpperCase() + ": " + message);
  }

  public static void notify(String message) {
    String logLevel = "info";
    // audio
    System.out.println(
        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " " + logLevel.toUpperCase() + ": "
            + message);
  }
}
