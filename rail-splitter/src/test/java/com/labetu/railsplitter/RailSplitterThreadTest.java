package com.labetu.railsplitter;

import static com.labetu.railsplitter.RailSplitter.railSplit;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RailSplitterThreadTest {

  private static final Logger rog = railSplit(LoggerFactory.getLogger(RailSplitterThreadTest.class));

  @Test
  public void test_thread_safety() throws InterruptedException {

    final Thread log1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          rog.info("First");
          Thread.sleep(500);
          assertEquals(1, RailSplitter.flush());
        } catch (InterruptedException e) {
          fail();
        }

      }
    });

    final Thread log2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(250);
          rog.info("Second");
          assertEquals(1, RailSplitter.flush());
        } catch (InterruptedException e) {
          fail();
        }
      }
    });

    log1.start();
    log2.start();

    log1.join();
    log2.join();

  }

}
