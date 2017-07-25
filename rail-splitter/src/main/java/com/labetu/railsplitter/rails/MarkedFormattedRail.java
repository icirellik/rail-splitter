package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import org.immutables.value.Value;
import org.slf4j.Logger;
import org.slf4j.Marker;

@Value.Immutable
public interface MarkedFormattedRail extends Rail {

  /**
   * The log message format.
   *
   * @return The message format.
   */
  String getFormat();

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
   * The list of object that should be logged.
   *
   * TODO: Is this going to cause a memory leak, should these be weak references.
   *
   * @return The objects to log.
   */
  Object[] getObjects();

  @Override
  @Value.Derived
  default int write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getMarker(), getFormat(), getObjects());
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getMarker(), getFormat(), getObjects());
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getMarker(), getFormat(), getObjects());
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getMarker(), getFormat(), getObjects());
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getMarker(), getFormat(), getObjects());
    } else {
      return 0;
    }
    return 1;
  }

}
