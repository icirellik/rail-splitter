package com.labetu.railsplitter;

public final class Level {

  public static final Level TRACE = new Level((short)0);

  public static final Level DEBUG = new Level((short)1);

  public static final Level INFO = new Level((short)2);

  public static final Level WARN = new Level((short)3);

  public static final Level ERROR = new Level((short)4);

  private final short level;

  private Level(final short level) {
    this.level = level;
  }

  public short getLevel() {
    return level;
  }

}
