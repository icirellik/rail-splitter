package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import org.immutables.value.Value;
import org.slf4j.Logger;

@Value.Immutable
public interface StandardRail extends Rail {

  @Override
  Level getLevel();

  @Override
  Logger getLogger();

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
      getLogger().debug(getMessage());
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getMessage());
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getMessage());
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getMessage());
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getMessage());
    }
  }

}
