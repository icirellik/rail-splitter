package com.labetu.railsplitter.rails;

import java.lang.ref.SoftReference;
import org.immutables.value.Value;

@Value.Immutable
public interface SoftObjectReference {

  @Value.Parameter
  SoftReference<Object> getObject();

}
