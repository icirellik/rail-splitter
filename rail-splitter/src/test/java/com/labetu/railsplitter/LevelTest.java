package com.labetu.railsplitter;

import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

public class LevelTest {

  @Test
  public void test_log_levels() {
    assertTrue(Level.OFF > Level.ERROR);
    assertTrue(Level.ERROR > Level.WARN);
    assertTrue(Level.WARN > Level.INFO);
    assertTrue(Level.INFO > Level.DEBUG);
    assertTrue(Level.DEBUG > Level.TRACE);
  }

}
