package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import org.immutables.value.Value;
import org.slf4j.Logger;
import org.slf4j.Marker;

@Value.Immutable
public interface MarkedStandardRail extends Rail {

  @Override
  Level getLevel();

  @Override
  Logger getLogger();

  /**
   * TODO: What is a marker.
   *
   * @return The marker.
   */
  Marker getMarker();

  /**
   * The stored message that may be logged in the future.
   *
   * @return The message.
   */
  String getMessage();

  @Override
  @Value.Derived
  default void write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getMarker(), getMessage());
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getMarker(), getMessage());
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getMarker(), getMessage());
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getMarker(), getMessage());
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getMarker(), getMessage());
    }
  }

}
