package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import com.labetu.railsplitter.utility.Objects;
import java.util.List;
import org.immutables.value.Value;
import org.slf4j.Logger;

@Value.Immutable
public interface FormattedRail extends Rail {

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
   * The list of object that should be logged.
   *
   * @return The objects to log.
   */
  List<SoftObjectReference> getObjects();

  @Override
  @Value.Derived
  default int write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getFormat(), Objects.fromSoftReferences(getObjects()));
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getFormat(), Objects.fromSoftReferences(getObjects()));
    } else {
      return 0;
    }
    return 1;
  }

}
