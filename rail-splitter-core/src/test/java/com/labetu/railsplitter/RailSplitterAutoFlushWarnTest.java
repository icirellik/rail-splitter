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

public class RailSplitterAutoFlushWarnTest {

  private static final Logger log = rail(
      LoggerFactory.getLogger(RailSplitterAutoFlushWarnTest.class),
      Switchman.builder()
          .logOnLevel(com.labetu.railsplitter.Level.WARN)
          .previousLogs(2)
          .build()
  );

  @AfterClass
  public static void afterAll() {
    RailSplitter.clearThreadLocals();
  }

  @Test
  public void test_trace() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    log.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    log.error("This is a test error");
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

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    log.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    log.error("This is a test error");
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

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    log.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    log.error("This is a test error");
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

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    log.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    log.error("This is a test error");
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

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    assertEquals(3, RailSplitter.size());
    log.warn("This is a test warn");
    assertEquals(0, RailSplitter.size());
    log.error("This is a test error");
    assertEquals(0, RailSplitter.size());
    assertEquals(0, RailSplitter.flush());
  }

}
