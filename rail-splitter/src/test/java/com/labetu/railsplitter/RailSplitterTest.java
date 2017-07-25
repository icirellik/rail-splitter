package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.railSplit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterTest {

  private static final Logger log = LoggerFactory.getLogger(RailSplitterTest.class);

  private static final Logger rog = railSplit(LoggerFactory.getLogger(RailSplitterTest.class));

  @Test
  public void test_log() {
    log.warn("This is a test warn");
    rog.warn("This is a test warn");

    assertEquals(1, RailSplitter.flush());
  }

}
