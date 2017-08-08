package com.labetu.railsplitter;

import org.immutables.value.Value;

/**
 * Tracks lifetime statistics for the current {@link RailTie}.
 */
@Value.Immutable
public interface RailStats {

  @Value.Derived
  default long getCount() {
    return getDebugCount()
        + getErrorCount()
        + getInfoCount()
        + getTraceCount()
        + getWarnCount();
  }

  long getDebugCount();

  long getErrorCount();

  long getInfoCount();

  long getTraceCount();

  long getWarnCount();

}
