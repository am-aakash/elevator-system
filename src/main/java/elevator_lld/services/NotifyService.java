package elevator_lld.services;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotifyService {
  private static final Logger log = LogManager.getLogger(NotifyService.class);

  public static void notify(String message, String logLevel) { // logLevel - info, debug, warn, error
    // audio
    System.out.println(LocalDateTime.now() + " " + logLevel.toUpperCase() + ": " + message);
  }

  public static void notify(String message) {
    String logLevel = "info";
    // audio
    log.info(message);
    System.out.println(LocalDateTime.now() + " " + logLevel.toUpperCase() + ": " + message);
  }
}
