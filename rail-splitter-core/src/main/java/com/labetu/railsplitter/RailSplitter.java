package com.labetu.railsplitter;

import org.slf4j.Logger;

public final class RailSplitter extends RailTieBase {

  private static ThreadLocal<Switchman> configuration;

  /**
   * Creates a new {@link RailSplitter} with a stored logger.
   *
   * @param logger The logger to write messages with.
   */
  public RailSplitter(final Logger logger) {
    super(logger, configuration.get());
  }

  /**
   * Creates a new {@link RailSplitter} that decorated the current logger.
   *
   * @param logger The logger to decorate.
   * @return The rail splitter.
   */
  public static RailTie rail(final Logger logger) {
    return rail(logger, Switchman.builder().build());
  }

  /**
   * Creates a new {@link RailSplitter} with a custom configuration.
   *
   * @param logger The logger to decorate.
   * @param configuration The configuration to use.
   * @return The rail splitter.
   */
  public static RailTie rail(final Logger logger, final Switchman configuration) {

    if (RailSplitter.configuration != null && RailSplitter.configuration.get() != null) {
      logger.warn("The current thread has been configured and may not be overridden by {}",
          logger.getName());
    } else {
      RailSplitter.configuration = new ThreadLocal<>();
      RailSplitter.configuration.set(configuration);
    }

    if (RailSplitter.configuration.get().statistics()) {
      return new RailTieStatisticsCollector(new RailSplitter(logger));
    }
    return new RailSplitter(logger);
  }

  /**
   * Manually clear the log buffer.
   */
  public static void clear() {
    getQueue().clear();
  }

  /**
   * Flushes all stored log messages.
   *
   * @return The number of log messages flushed.
   */
  public static int flush() {
    final int written = getQueue().stream().map(Rail::write).mapToInt(x -> x).sum();
    // TODO: Verify that this is required.
    getQueue().clear();
    return written;
  }

  /**
   * The number of messages that have been queued.
   *
   * @return The queued messages.
   */
  public static int size() {
    return getQueue().size();
  }

  /**
   * Removes all thread local objects.
   */
  public static void clearThreadLocals() {
    if (configuration != null && configuration.get() != null) {
      configuration.remove();
    }
    if (messageQueue != null && messageQueue.get() != null) {
      messageQueue.remove();
    }
  }

}
