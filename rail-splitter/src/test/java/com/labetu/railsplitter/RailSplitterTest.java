package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.railSplit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterTest {

  private static final String CONFIG_PATTERN =
      "{date:yyyy-MM-dd'T'HH:mm:ssZ} [{thread}] {level} {class_name}:{line} {message}";

  private static final Logger log = LoggerFactory.getLogger(RailSplitterTest.class);

  private static final Logger rog = railSplit(LoggerFactory.getLogger(RailSplitterTest.class));

  @Test
  public void test_log() {
    log.warn("This is a test warn");
    rog.warn("This is a test warn");

    assertEquals(1, RailSplitter.flush());
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
    rog.warn("This is a test warn");
    rog.error("This is a test error");

    assertEquals(5, RailSplitter.flush());
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
    rog.warn("This is a test warn");
    rog.error("This is a test error");

    assertEquals(4, RailSplitter.flush());
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
    rog.warn("This is a test warn");
    rog.error("This is a test error");

    assertEquals(3, RailSplitter.flush());
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
    rog.warn("This is a test warn");
    rog.error("This is a test error");

    assertEquals(2, RailSplitter.flush());
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
    rog.warn("This is a test warn");
    rog.error("This is a test error");

    assertEquals(1, RailSplitter.flush());
  }

  @Test
  public void test_clear() {
    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.trace("This is a test trace");
    rog.debug("This is a test debug");
    rog.info("This is a test info");
    rog.warn("This is a test warn");
    rog.error("This is a test error");

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

    rog.trace("This is a test {}", "apple");
    rog.trace("This is a test {} {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_debug_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.debug("This is a test {}", "apple");
    rog.debug("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_info_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.info("This is a test {}", "apple");
    rog.info("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_warn_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.warn("This is a test {}", "apple");
    rog.warn("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

  @Test
  public void test_error_format_object() {

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    rog.error("This is a test {}", "apple");
    rog.error("This is a test {}", 1, "banana");

    assertEquals(2, RailSplitter.flush());
  }

}
