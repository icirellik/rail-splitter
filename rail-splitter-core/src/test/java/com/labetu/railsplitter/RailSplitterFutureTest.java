package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.rail;
import static com.labetu.railsplitter.TestContants.CONFIG_PATTERN;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterFutureTest {

  private static final Logger log = rail(
      LoggerFactory.getLogger(RailSplitterFutureTest.class),
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
  }

  @AfterClass
  public static void afterAll() {
    RailSplitter.clearThreadLocals();
  }

  /**
   * This test should buffer two messages, hit the trigger and flush three. Then log one future
   * message and ignore any future messages.
   */
  @Test
  public void test_future_logs() {

    // The standard logs.
    log.trace("(buffered) This is a test trace");
    log.debug("(buffered) This is a test debug");
    log.info("(trigger) This is a test info");
    log.debug("(future) This is a test debug");
    log.trace("(ignored) This is a test trace");
    log.trace("(ignored) This is a test trace");

    assertEquals(2, RailSplitter.size());
    assertEquals(2, RailSplitter.flush());
  }

}
