package com.labetu.railsplitter.rails;

import com.labetu.railsplitter.Level;
import com.labetu.railsplitter.Rail;
import org.immutables.value.Value;
import org.slf4j.Logger;

@Value.Immutable
public interface ThrowableRail extends Rail {

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

  /**
   * The exception that was thrown.
   *
   * @return The exception.
   */
  Throwable getThrowable();

  @Override
  @Value.Derived
  default void write() {
    if (getLevel() == Level.DEBUG && getLogger().isDebugEnabled()) {
      getLogger().debug(getMessage(), getThrowable());
    } else if (getLevel() == Level.ERROR && getLogger().isErrorEnabled()) {
      getLogger().error(getMessage(), getThrowable());
    } else if (getLevel() == Level.INFO && getLogger().isInfoEnabled()) {
      getLogger().info(getMessage(), getThrowable());
    } else if (getLevel() == Level.TRACE && getLogger().isTraceEnabled()) {
      getLogger().trace(getMessage(), getThrowable());
    } else if (getLevel() == Level.WARN && getLogger().isWarnEnabled()) {
      getLogger().warn(getMessage(), getThrowable());
    }
  }

}
