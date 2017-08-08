package com.labetu.railsplitter;

import static com.google.common.base.Preconditions.checkState;

/**
 * The {@link Switchman} manages the individual configuration settings that are available.
 */
public final class Switchman {

  /**
   * Enables logging of all messages.
   */
  public static final int LOG_EVERY_MESSAGE = 1 << 1;

  /**
   * Enables the ability to log messages that occur directly after logging an exception.
   */
  public static final int LOG_FUTURE_MESSAGES = 1 << 2;

  /**
   * The number of logs to write after an exception occurs. This can be used to get more insight
   * into where an exception occurred.
   */
  private final int futureLogs;

  /**
   * The currently enabled features.
   */
  private final int features;

  /**
   * The level at which the queue will be flushed.
   */
  private final short logOnLevel;

  /**
   * The number of previous logs to write when an exception occurs in the case that no transaction
   * point has been set.
   */
  private final int previousLogs;

  /**
   * Are statistics enabled?
   */
  private final boolean statistics;

  /**
   *
   * @param previousLogs The number of previous messages to log.
   * @param futureLogs The number of future messages to log when a flush occurs.
   * @param features The features to enable.
   * @param logOnLevel The level that causes a log flush when seen.
   * @param statistics Enables statistics.
   */
  private Switchman(
      final int previousLogs,
      final int futureLogs,
      final int features,
      final short logOnLevel,
      final boolean statistics
  ) {
    this.previousLogs = previousLogs;
    this.futureLogs = futureLogs;
    this.features = features;
    this.logOnLevel = logOnLevel;
    this.statistics = statistics;
  }

  public int getPreviousLogs() {
    return  previousLogs;
  }

  public int getLogFutureMessages() {
    return futureLogs;
  }

  public boolean logAllMessages() {
    return (features & LOG_EVERY_MESSAGE) == LOG_EVERY_MESSAGE;
  }

  public boolean logFutureMessages() {
    return (features & LOG_FUTURE_MESSAGES) == LOG_FUTURE_MESSAGES;
  }

  public short getLogOnLevel() {
    return logOnLevel;
  }

  public boolean statistics() {
    return statistics;
  }

  public static Builder builder() {
      return new Builder();
  }

  /**
   * The {@link Switchman} builder.
   */
  public static class Builder {

    private int features;

    private int futureLogs;

    private short logOnLevel;

    private int previousLogs;

    private boolean statistics;

    private Builder() {
      features = 0;
      futureLogs = 0;
      logOnLevel = Level.ERROR;
      previousLogs = 512;
    }

    public Builder features(final int features) {
      this.features = features;
      return this;
    }

    public Builder futureLogs(final int futureLogs) {
      this.futureLogs = futureLogs;
      return this;
    }

    public Builder logOnLevel(final short logOnLevel) {
      this.logOnLevel = logOnLevel;
      return this;
    }

    public Builder previousLogs(final int previousLogs) {
      this.previousLogs = previousLogs;
      return this;
    }

    public Builder statistics(final boolean statistics) {
      this.statistics = statistics;
      return this;
    }

    /**
     * Creates a new {@link Switchman} with the specified options.
     *
     * @return The new configuration.
     */
    public Switchman build() {

      checkState(features > -1, "'features' must be 0 or greater");
      checkState(futureLogs > -1, "'futureLogs' must be 0 or greater");
      checkState(previousLogs > 0, "'previousLogs' must be greater than 0");

      return new Switchman(previousLogs, futureLogs, features, logOnLevel, statistics);
    }

  }


}
