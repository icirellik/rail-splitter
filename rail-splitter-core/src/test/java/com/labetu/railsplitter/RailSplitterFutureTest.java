package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.rail;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterFutureTest {

  private static final String CONFIG_PATTERN =
      "{date:yyyy-MM-dd'T'HH:mm:ssZ} [{thread}] {level} {class_name}:{line} {message}";

  private static final Logger log = LoggerFactory.getLogger(RailSplitterFutureTest.class);

  private static final Logger rog = rail(
      log,
      Switchman.builder()
          .futureLogs(1)
          .logOnLevel(com.labetu.railsplitter.Level.DEBUG)
          .build()
  );

  @BeforeClass
  public static void beforeAll() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    RailSplitter.clearThreadLocals();
  }

  /**
   * This test should buffer two messages, hit the trigger and flush three. Then log one future
   * message and ignore any future messages.
   */
  @Test
  public void test_future_logs() {

    // The standard logs.
    rog.trace("(buffered) This is a test trace");
    rog.debug("(buffered) This is a test debug");
    rog.info("(trigger) This is a test info");
    rog.debug("(future) This is a test debug");
    rog.trace("(ignored) This is a test trace");
    rog.trace("(ignored) This is a test trace");

    assertEquals(2, RailSplitter.size());
    assertEquals(2, RailSplitter.flush());
  }

}
