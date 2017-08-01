package com.labetu.railsplitter;

import org.slf4j.Logger;

/**
 * A {@link Rail} represents a single log message.
 */
public interface Rail  {

  /**
   * The level at which to log a message.
   *
   * @return The log level.
   */
  short getLevel();

  /**
   * Tracks the logger so that we can log which class, etc the original log came from.
   *
   * @return The original logger.
   */
  Logger getLogger();

  /**
   * Writes the stored log to the saved logger.
   *
   * @return The number of messages logged.
   */
  int write();

}
