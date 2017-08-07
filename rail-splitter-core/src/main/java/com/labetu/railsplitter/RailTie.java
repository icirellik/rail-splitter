package com.labetu.railsplitter;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.collect.EvictingQueue;
import com.labetu.railsplitter.rails.ImmutableFormattedRail;
import com.labetu.railsplitter.rails.ImmutableMarkedFormattedRail;
import com.labetu.railsplitter.rails.ImmutableMarkedStandardRail;
import com.labetu.railsplitter.rails.ImmutableMarkedThrowableRail;
import com.labetu.railsplitter.rails.ImmutableStandardRail;
import com.labetu.railsplitter.rails.ImmutableThrowableRail;
import com.labetu.railsplitter.utility.Objects;
import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * A {@link RailTie} is the slf4j decorator, this class wraps current log calls and stores them for
 * later usage.
 */
abstract class RailTie implements Logger {

  protected static final int DEFAULT_MESSAGE_QUEUE_SIZE = 512;

  protected static final ThreadLocal<EvictingQueue<Rail>> messageQueue =
      ThreadLocal.withInitial(() -> EvictingQueue.create(DEFAULT_MESSAGE_QUEUE_SIZE));

  private final Switchman configuration;

  private final Logger logger;

  /**
   * The number of future message remaining to be logged after flush event has occurred.
   *
   * TODO: Make this immutable.
   */
  private int futureMessagesRemaining;

  /**
   * Creates a new {@link RailTie} with a stored logger.
   *
   * @param logger The logger to write messages with.
   * @param configuration The configuration to use.
   */
  RailTie(final Logger logger, final Switchman configuration) {
    this.configuration = configuration;
    this.logger = logger;
  }

  public void cleanup() {
    messageQueue.remove();
  }

  /**
   * Manually clear the log buffer.
   */
  private void clearQueue() {
    getQueue().clear();
  }

  /**
   * Flushes all stored log messages.
   *
   * @return The number of log messages flushed.
   */
  private void flushQueue() {
    final int written;
    if (configuration.logAllMessages()
        || configuration.getPreviousLogs() >= DEFAULT_MESSAGE_QUEUE_SIZE) {
      written = getQueue().stream().map(Rail::write).mapToInt(x -> x).sum();
    } else if (futureMessagesRemaining > 0) {
      written = getQueue().remove().write();
      futureMessagesRemaining--;
    } else {
      for (int i = getQueue().size() - configuration.getPreviousLogs(); i > 0; i--) {
        getQueue().remove();
      }
      written = getQueue().stream().map(Rail::write).mapToInt(x -> x).sum();
    }
    if (configuration.logFutureMessagees()) {
      futureMessagesRemaining = configuration.getLogFutureMessages();
    }
    // TODO: Verify that this is required.
    getQueue().clear();
    checkState(written >= 0, "Logs flushed cannot be negative.");
  }

  /**
   * Gets the thread local message queue.
   *
   * @return The message queue.
   */
  public static EvictingQueue<Rail> getQueue() {
    return messageQueue.get();
  }

  @Override
  public String getName() {
    return "RS:" + logger.getName();
  }

  @Override
  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  @Override
  public void trace(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public void trace(String format, Object arg) {
    trace(format, new Object[] { arg });
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    trace(format, new Object[] { arg1, arg2 });
  }

  @Override
  public void trace(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.TRACE)
        .logger(logger)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public void trace(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return logger.isTraceEnabled(marker);
  }

  @Override
  public void trace(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
    trace(marker, format, new Object[] { arg });
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    trace(marker, format, new Object[] { arg1, arg2 });
  }

  @Override
  public void trace(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.TRACE)
        .logger(logger)
        .marker(marker)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.TRACE) {
      flushQueue();
    }
  }

  @Override
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  @Override
  public void debug(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public void debug(String format, Object arg) {
    debug(format, new Object[] { arg });
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    debug(format, new Object[] { arg1, arg2 });
  }

  @Override
  public void debug(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.DEBUG)
        .logger(logger)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public void debug(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return logger.isDebugEnabled(marker);
  }

  @Override
  public void debug(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
    debug(marker, format, new Object[] { arg });
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    debug(marker, format, new Object[] { arg1, arg2 });
  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.DEBUG)
        .logger(logger)
        .marker(marker)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.DEBUG) {
      flushQueue();
    }
  }

  @Override
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  @Override
  public void info(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public void info(String format, Object arg) {
    info(format, new Object[] { arg });
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    info(format, new Object[] { arg1, arg2 });
  }

  @Override
  public void info(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.INFO)
        .logger(logger)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public void info(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return logger.isInfoEnabled(marker);
  }

  @Override
  public void info(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
    info(marker, format, new Object[] { arg });
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
    info(marker, format, new Object[] { arg1, arg2 });
  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.INFO)
        .logger(logger)
        .marker(marker)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.INFO) {
      flushQueue();
    }
  }

  @Override
  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  @Override
  public void warn(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public void warn(String format, Object arg) {
    warn(format, new Object[] { arg });
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    warn(format, new Object[] { arg1, arg2 });
  }

  @Override
  public void warn(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.WARN)
        .logger(logger)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public void warn(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return logger.isWarnEnabled(marker);
  }

  @Override
  public void warn(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
    warn(marker, format, new Object[] { arg });
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    warn(marker, format, new Object[] { arg1, arg2 });
  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.WARN)
        .logger(logger)
        .marker(marker)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.WARN) {
      flushQueue();
    }
  }

  @Override
  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  @Override
  public void error(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

  @Override
  public void error(String format, Object arg) {
    error(format, new Object[] { arg });
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    error(format, new Object[] { arg1, arg2 });
  }

  @Override
  public void error(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.ERROR)
        .logger(logger)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

  @Override
  public void error(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return logger.isErrorEnabled(marker);
  }

  @Override
  public void error(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
    error(marker, format, new Object[] { arg });
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
    error(marker, format, new Object[] { arg1, arg2 });
  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.ERROR)
        .logger(logger)
        .marker(marker)
        .objects(Objects.toSoftReferences(arguments))
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
    if (configuration.getLogOnLevel() <= Level.ERROR) {
      flushQueue();
    }
  }

}
