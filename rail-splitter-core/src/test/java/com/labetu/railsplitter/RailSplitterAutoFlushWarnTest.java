package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.rail;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterAutoFlushWarnTest {

  private static final String CONFIG_PATTERN =
      "{date:yyyy-MM-dd'T'HH:mm:ssZ} [{thread}] {level} {class_name}:{line} {message}";

  private static final Logger log = LoggerFactory.getLogger(RailSplitterAutoFlushWarnTest.class);

  private static final Logger rog = rail(
      log,
      Switchman.builder()
          .logOnLevel(com.labetu.railsplitter.Level.WARN)
          .previousLogs(2)
          .build()
  );

  @BeforeClass
  public static void beforeAll() {
    RailSplitter.clearThreadLocals();
  }

  @Test
  public void test_trace() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    rog.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    rog.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

  @Test
  public void test_debug() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.DEBUG)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    rog.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    rog.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

  @Test
  public void test_info() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.INFO)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    rog.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    rog.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

  @Test
  public void test_warn() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.WARNING)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    rog.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    rog.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

  @Test
  public void test_error() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.ERROR)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    rog.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    rog.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

}
