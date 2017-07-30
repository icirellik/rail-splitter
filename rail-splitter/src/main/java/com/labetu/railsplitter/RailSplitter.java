package com.labetu.railsplitter;

import org.slf4j.Logger;

public final class RailSplitter extends RailTie {

  /**
   * Creates a new rail splitter that decorated the current logger.
   *
   * @param logger The logger to decorate.
   * @return The rail splitter.
   */
  public static RailSplitter railSplit(final Logger logger) {
    return new RailSplitter(logger);
  }

  /**
   * Flushes all stored log messages.
   *
   * @return The number of log messages flushed.
   */
  public static int flush() {
    final int written = getQueue().stream().map(Rail::write).mapToInt(x -> x).sum();
    getQueue().clear();
    return written;
  }

  /**
   * Manually clear the log buffer.
   */
  public static void clear() {
    getQueue().clear();
  }

  /**
   * Creates a new {@link RailSplitter} with a stored logger.
   *
   * @param logger The logger to write messages with.
   */
  public RailSplitter(final Logger logger) {
    super(logger);
  }

}
