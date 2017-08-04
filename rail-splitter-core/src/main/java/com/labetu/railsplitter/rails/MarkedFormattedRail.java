package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import com.labetu.railsplitter.utility.Objects;
import java.util.List;
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
  short getLevel();

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
   * @return The objects to log.
   */
  List<SoftObjectReference> getObjects();

  @Override
  @Value.Derived
  default int write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getMarker(), getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getMarker(), getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getMarker(), getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getMarker(), getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getMarker(), getFormat(), Objects.fromSoftReferences(getObjects()));
    } else {
      return 0;
    }
    return 1;
  }

}
