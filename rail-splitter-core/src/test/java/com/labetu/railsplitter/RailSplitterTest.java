package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.rail;
import static com.labetu.railsplitter.TestContants.CONFIG_PATTERN;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterTest {

  private static final Logger log = rail(
      LoggerFactory.getLogger(RailSplitterTest.class),
      Switchman.builder().logOnLevel(com.labetu.railsplitter.Level.OFF).build()
  );

  @AfterClass
  public static void afterAll() {
    RailSplitter.clearThreadLocals();
  }

  @Test
  public void test_log() {
    log.warn("This is a test warn");

    assertEquals(1, RailSplitter.flush());
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
    log.warn("This is a test warn");
    log.error("This is a test error");

    assertEquals(5, RailSplitter.size());
    assertEquals(5, RailSplitter.flush());
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
    log.warn("This is a test warn");
    log.error("This is a test error");

    assertEquals(5, RailSplitter.size());
    assertEquals(4, RailSplitter.flush());
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
    log.warn("This is a test warn");
    log.error("This is a test error");

    assertEquals(5, RailSplitter.size());
    assertEquals(3, RailSplitter.flush());
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
    log.warn("This is a test warn");
    log.error("This is a test error");

    assertEquals(5, RailSplitter.size());
    assertEquals(2, RailSplitter.flush());
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
    log.warn("This is a test warn");
    log.error("This is a test error");

    assertEquals(5, RailSplitter.size());
    assertEquals(1, RailSplitter.flush());
  }

  @Test
  public void test_clear() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    log.warn("This is a test warn");
    log.error("This is a test error");

    RailSplitter.clear();

    assertEquals(0, RailSplitter.flush());
  }

  @Test
  public void test_trace_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.trace("This is a test {}", "apple");
    log.trace("This is a test {} {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_debug_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.debug("This is a test {}", "apple");
    log.debug("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_info_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.info("This is a test {}", "apple");
    log.info("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_warn_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.warn("This is a test {}", "apple");
    log.warn("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_error_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.error("This is a test {}", "apple");
    log.error("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

}
