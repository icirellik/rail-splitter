package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
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
   * TODO: Is this going to cause a memory leak, should these be weak references.
   *
   * @return The objects to log.
   */
  Object[] getObjects();

  @Override
  @Value.Derived
  default int write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getFormat(), getObjects());
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getFormat(), getObjects());
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getFormat(), getObjects());
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getFormat(), getObjects());
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getFormat(), getObjects());
    } else {
      return 0;
    }
    return 1;
  }

}
