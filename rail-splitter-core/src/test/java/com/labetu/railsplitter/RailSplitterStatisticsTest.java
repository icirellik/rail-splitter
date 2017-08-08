package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.rail;
import static com.labetu.railsplitter.TestContants.CONFIG_PATTERN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.slf4j.LoggerFactory;

public class RailSplitterStatisticsTest {

  @Test
  public void test_basic_stats() {

    final RailTie log = rail(
        LoggerFactory.getLogger(RailSplitterStatisticsTest.class),
        Switchman.builder()
            .logOnLevel(com.labetu.railsplitter.Level.OFF)
            .statistics(true)
            .build()
    );

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(org.pmw.tinylog.Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.info("This is a test info");
    log.warn("This is a test warn");
    log.error("This is a test error");

    final RailStats stats = log.getStatistics();
    assertNotNull(stats);
    assertEquals(1, stats.getTraceCount());
    assertEquals(1, stats.getDebugCount());
    assertEquals(1, stats.getInfoCount());
    assertEquals(1, stats.getWarnCount());
    assertEquals(1, stats.getErrorCount());
    assertEquals(5, stats.getCount());
    RailSplitter.clearThreadLocals();

  }

  @Test
  public void test_advanced_stats() {

    final RailTie log = rail(
        LoggerFactory.getLogger(RailSplitterStatisticsTest.class),
        Switchman.builder()
            .logOnLevel(com.labetu.railsplitter.Level.OFF)
            .statistics(true)
            .build()
    );

    Configurator.currentConfig()
        .formatPattern(CONFIG_PATTERN)
        .level(org.pmw.tinylog.Level.TRACE)
        .maxStackTraceElements(500)
        .activate();

    final RailStats stats1 = log.getStatistics();
    assertNotNull(stats1);
    assertEquals(0, stats1.getTraceCount());
    assertEquals(0, stats1.getDebugCount());
    assertEquals(0, stats1.getInfoCount());
    assertEquals(0, stats1.getWarnCount());
    assertEquals(0, stats1.getErrorCount());
    assertEquals(0, stats1.getCount());

    log.trace("This is a test trace");
    log.debug("This is a test debug");
    log.debug("This is a test debug");
    log.info("This is a test info");
    log.info("This is a test info");
    log.info("This is a test info");
    log.warn("This is a test warn");
    log.warn("This is a test warn");
    log.warn("This is a test warn");
    log.warn("This is a test warn");
    log.error("This is a test error");
    log.error("This is a test error");
    log.error("This is a test error");
    log.error("This is a test error");
    log.error("This is a test error");

    final RailStats stats2 = log.getStatistics();
    assertNotNull(stats2);
    assertEquals(1, stats2.getTraceCount());
    assertEquals(2, stats2.getDebugCount());
    assertEquals(3, stats2.getInfoCount());
    assertEquals(4, stats2.getWarnCount());
    assertEquals(5, stats2.getErrorCount());
    assertEquals(15, stats2.getCount());

    assertEquals(15, RailSplitter.flush());
    RailSplitter.clearThreadLocals();

  }

}
