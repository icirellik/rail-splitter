package com.labetu.railsplitter;

import org.slf4j.Marker;

/**
 * The {@link RailTieStatisticsCollector} is a decorator that can optionally be loaded to enable
 * statistics tracking at runtime. This obviously comes with some performance overhead and should
 * only be used when required.
 *
 * Note that the statistics tracked here are a count individual of the log functions called, not a
 * count of the messages actually flushed to STDOUT or STDERR.
 */
public class RailTieStatisticsCollector implements RailTie {

  private final RailTie railTie;

  private long debugCount;
  private long errorCount;
  private long infoCount;
  private long traceCount;
  private long warnCount;

  public RailTieStatisticsCollector(final RailTie railTie) {
    this.railTie = railTie;
  }

  @Override
  public RailStats getStatistics() {
    return ImmutableRailStats.builder()
        .debugCount(debugCount)
        .errorCount(errorCount)
        .infoCount(infoCount)
        .traceCount(traceCount)
        .warnCount(warnCount)
        .build();
  }

  @Override
  public String getName() {
    return railTie.getName();
  }

  @Override
  public boolean isDebugEnabled() {
    return railTie.isDebugEnabled();
  }

  @Override
  public void debug(String format) {
    debugCount++;
    railTie.debug(format);
  }

  @Override
  public void debug(String format, Object arg) {
    debugCount++;
    railTie.debug(format, arg);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    debugCount++;
    railTie.debug(format, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... arguments) {
    debugCount++;
    railTie.debug(format, arguments);
  }

  @Override
  public void debug(String format, Throwable throwable) {
    debugCount++;
    railTie.debug(format, throwable);
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return railTie.isDebugEnabled(marker);
  }

  @Override
  public void debug(Marker marker, String format) {
    debugCount++;
    railTie.debug(marker, format);
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
    debugCount++;
    railTie.debug(marker, format, arg);
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    debugCount++;
    railTie.debug(marker, format, arg1, arg2);
  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {
    debugCount++;
    railTie.debug(marker, format, arguments);
  }

  @Override
  public void debug(Marker marker, String format, Throwable throwable) {
    debugCount++;
    railTie.debug(marker, format, throwable);
  }

  @Override
  public boolean isErrorEnabled() {
    return railTie.isErrorEnabled();
  }

  @Override
  public void error(String format) {
    errorCount++;
    railTie.error(format);
  }

  @Override
  public void error(String format, Object arg) {
    errorCount++;
    railTie.error(format, arg);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    errorCount++;
    railTie.error(format, arg1, arg2);
  }

  @Override
  public void error(String format, Object... arguments) {
    errorCount++;
    railTie.error(format, arguments);
  }

  @Override
  public void error(String format, Throwable throwable) {
    errorCount++;
    railTie.error(format, throwable);
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return railTie.isErrorEnabled(marker);
  }

  @Override
  public void error(Marker marker, String format) {
    errorCount++;
    railTie.error(marker, format);
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
    errorCount++;
    railTie.error(marker, format, arg);
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
    errorCount++;
    railTie.error(marker, format, arg1, arg2);
  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {
    errorCount++;
    railTie.error(marker, format, arguments);
  }

  @Override
  public void error(Marker marker, String format, Throwable throwable) {
    errorCount++;
    railTie.error(marker, format, throwable);
  }

  @Override
  public boolean isInfoEnabled() {
    return railTie.isInfoEnabled();
  }

  @Override
  public void info(String format) {
    infoCount++;
    railTie.info(format);
  }

  @Override
  public void info(String format, Object arg) {
    infoCount++;
    railTie.info(format, arg);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    infoCount++;
    railTie.info(format, arg1, arg2);
  }

  @Override
  public void info(String format, Object... arguments) {
    infoCount++;
    railTie.info(format, arguments);
  }

  @Override
  public void info(String format, Throwable throwable) {
    infoCount++;
    railTie.info(format, throwable);
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return railTie.isInfoEnabled(marker);
  }

  @Override
  public void info(Marker marker, String format) {
    infoCount++;
    railTie.info(marker, format);
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
    infoCount++;
    railTie.info(marker, format, arg);
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
    infoCount++;
    railTie.info(marker, format, arg1, arg2);
  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {
    infoCount++;
    railTie.info(marker, format, arguments);
  }

  @Override
  public void info(Marker marker, String format, Throwable throwable) {
    infoCount++;
    railTie.info(marker, format, throwable);
  }

  @Override
  public boolean isTraceEnabled() {
    return railTie.isTraceEnabled();
  }

  @Override
  public void trace(String format) {
    traceCount++;
    railTie.trace(format);
  }

  @Override
  public void trace(String format, Object arg) {
    traceCount++;
    railTie.trace(format, arg);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    traceCount++;
    railTie.trace(format, arg1, arg2);
  }

  @Override
  public void trace(String format, Object... arguments) {
    traceCount++;
    railTie.trace(format, arguments);
  }

  @Override
  public void trace(String format, Throwable throwable) {
    traceCount++;
    railTie.trace(format, throwable);
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return railTie.isTraceEnabled(marker);
  }

  @Override
  public void trace(Marker marker, String format) {
    traceCount++;
    railTie.trace(marker, format);
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
    traceCount++;
    railTie.trace(marker, format, arg);
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    traceCount++;
    railTie.trace(marker, format, arg1, arg2);
  }

  @Override
  public void trace(Marker marker, String format, Object... arguments) {
    traceCount++;
    railTie.trace(marker, format, arguments);
  }

  @Override
  public void trace(Marker marker, String format, Throwable throwable) {
    traceCount++;
    railTie.trace(marker, format, throwable);
  }

  @Override
  public boolean isWarnEnabled() {
    return railTie.isWarnEnabled();
  }

  @Override
  public void warn(String format) {
    warnCount++;
    railTie.warn(format);
  }

  @Override
  public void warn(String format, Object arg) {
    warnCount++;
    railTie.warn(format, arg);
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    warnCount++;
    railTie.warn(format, arg1, arg2);
  }

  @Override
  public void warn(String format, Object... arguments) {
    warnCount++;
    railTie.warn(format, arguments);
  }

  @Override
  public void warn(String format, Throwable throwable) {
    warnCount++;
    railTie.warn(format, throwable);
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return railTie.isWarnEnabled(marker);
  }

  @Override
  public void warn(Marker marker, String format) {
    warnCount++;
    railTie.warn(marker, format);
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
    warnCount++;
    railTie.warn(marker, format, arg);
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    warnCount++;
    railTie.warn(marker, format, arg1, arg2);
  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {
    warnCount++;
    railTie.warn(marker, format, arguments);
  }

  @Override
  public void warn(Marker marker, String format, Throwable throwable) {
    warnCount++;
    railTie.warn(marker, format, throwable);
  }

}
