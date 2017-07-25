package com.labetu.railsplitter;

import com.google.common.collect.EvictingQueue;
import com.labetu.railsplitter.rails.ImmutableFormattedRail;
import com.labetu.railsplitter.rails.ImmutableMarkedFormattedRail;
import com.labetu.railsplitter.rails.ImmutableMarkedStandardRail;
import com.labetu.railsplitter.rails.ImmutableMarkedThrowableRail;
import com.labetu.railsplitter.rails.ImmutableStandardRail;
import com.labetu.railsplitter.rails.ImmutableThrowableRail;
import org.slf4j.Logger;
import org.slf4j.Marker;

public final class RailSplitter implements org.slf4j.Logger {

  private static final int DEFAULT_MESSAGE_QUEUE_SIZE = 4_096;

  private static final ThreadLocal<EvictingQueue<Rail>> messageQueue =
      ThreadLocal.withInitial(() -> EvictingQueue.create(DEFAULT_MESSAGE_QUEUE_SIZE));

  private final Logger logger;

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
    final int messageCount = messageQueue.get().size();
    messageQueue.get().stream().forEach(Rail::write);
    return messageCount;
  }

  /**
   * Creates a new {@link RailSplitter} with a stored logger.
   *
   * @param logger The logger to write messages with.
   */
  public RailSplitter(final Logger logger) {
    this.logger = logger;
  }

  public String getName() {
    return "RS:" + logger.getName();
  }

  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  public void trace(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .message(msg)
        .build()
    );
  }

  public void trace(String format, Object arg) {
    trace(format, new Object[] { arg });
  }

  public void trace(String format, Object arg1, Object arg2) {
    trace(format, new Object[] { arg1, arg2 });
  }

  public void trace(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.TRACE)
        .objects(arguments)
        .build()
    );
  }

  public void trace(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isTraceEnabled(Marker marker) {
    return logger.isTraceEnabled(marker);
  }

  public void trace(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
  }

  public void trace(Marker marker, String format, Object arg) {
    trace(marker, format, new Object[] { arg });
  }

  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    trace(marker, format, new Object[] { arg1, arg2 });
  }

  public void trace(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.TRACE)
        .marker(marker)
        .objects(arguments)
        .build()
    );
  }

  public void trace(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.TRACE)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  public void debug(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .message(msg)
        .build()
    );
  }

  public void debug(String format, Object arg) {
    debug(format, new Object[] { arg });
  }

  public void debug(String format, Object arg1, Object arg2) {
    debug(format, new Object[] { arg1, arg2 });
  }

  public void debug(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.DEBUG)
        .objects(arguments)
        .build()
    );
  }

  public void debug(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isDebugEnabled(Marker marker) {
    return logger.isDebugEnabled(marker);
  }

  public void debug(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
  }

  public void debug(Marker marker, String format, Object arg) {
    debug(marker, format, new Object[] { arg });
  }

  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    debug(marker, format, new Object[] { arg1, arg2 });
  }

  public void debug(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.DEBUG)
        .marker(marker)
        .objects(arguments)
        .build()
    );
  }

  public void debug(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.DEBUG)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  public void info(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .message(msg)
        .build()
    );
  }

  public void info(String format, Object arg) {
    info(format, new Object[] { arg });
  }

  public void info(String format, Object arg1, Object arg2) {
    info(format, new Object[] { arg1, arg2 });
  }

  public void info(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.INFO)
        .objects(arguments)
        .build()
    );
  }

  public void info(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isInfoEnabled(Marker marker) {
    return logger.isInfoEnabled(marker);
  }

  public void info(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
  }

  public void info(Marker marker, String format, Object arg) {
    info(marker, format, new Object[] { arg });
  }

  public void info(Marker marker, String format, Object arg1, Object arg2) {
    info(marker, format, new Object[] { arg1, arg2 });
  }

  public void info(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.INFO)
        .marker(marker)
        .objects(arguments)
        .build()
    );
  }

  public void info(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.INFO)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  public void warn(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .message(msg)
        .build()
    );
  }

  public void warn(String format, Object arg) {
    warn(format, new Object[] { arg });
  }

  public void warn(String format, Object arg1, Object arg2) {
    warn(format, new Object[] { arg1, arg2 });
  }

  public void warn(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.WARN)
        .objects(arguments)
        .build()
    );
  }

  public void warn(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isWarnEnabled(Marker marker) {
    return logger.isWarnEnabled(marker);
  }

  public void warn(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
  }

  public void warn(Marker marker, String format, Object arg) {
    warn(marker, format, new Object[] { arg });
  }

  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    warn(marker, format, new Object[] { arg1, arg2 });
  }

  public void warn(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.WARN)
        .marker(marker)
        .objects(arguments)
        .build()
    );
  }

  public void warn(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.WARN)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  public void error(String msg) {
    messageQueue.get().add(ImmutableStandardRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .message(msg)
        .build()
    );
  }

  public void error(String format, Object arg) {
    error(format, new Object[] { arg });
  }

  public void error(String format, Object arg1, Object arg2) {
    error(format, new Object[] { arg1, arg2 });
  }

  public void error(String format, Object... arguments) {
    messageQueue.get().add(ImmutableFormattedRail.builder()
        .format(format)
        .level(Level.ERROR)
        .objects(arguments)
        .build()
    );
  }

  public void error(String msg, Throwable t) {
    messageQueue.get().add(ImmutableThrowableRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

  public boolean isErrorEnabled(Marker marker) {
    return logger.isErrorEnabled(marker);
  }

  public void error(Marker marker, String msg) {
    messageQueue.get().add(ImmutableMarkedStandardRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .build()
    );
  }

  public void error(Marker marker, String format, Object arg) {
    error(marker, format, new Object[] { arg });
  }

  public void error(Marker marker, String format, Object arg1, Object arg2) {
    error(marker, format, new Object[] { arg1, arg2 });
  }

  public void error(Marker marker, String format, Object... arguments) {
    messageQueue.get().add(ImmutableMarkedFormattedRail.builder()
        .format(format)
        .level(Level.ERROR)
        .marker(marker)
        .objects(arguments)
        .build()
    );
  }

  public void error(Marker marker, String msg, Throwable t) {
    messageQueue.get().add(ImmutableMarkedThrowableRail.builder()
        .level(Level.ERROR)
        .logger(logger)
        .marker(marker)
        .message(msg)
        .throwable(t)
        .build()
    );
  }

}
