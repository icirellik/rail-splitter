package com.labetu.railsplitter.utility;

import com.google.common.collect.Lists;
import com.labetu.railsplitter.rails.ImmutableSoftObjectReference;
import com.labetu.railsplitter.rails.SoftObjectReference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link Objects} class contains utility function  that make working with different object
 * types easier.
 */
public final class Objects {

  /**
   * Transforms an array of object soft references into an object array.
   *
   * @param softObjects The soft object references.
   * @return The object array.
   */
  public static Object[] fromSoftReferences(final List<SoftObjectReference> softObjects) {
    final Object[] objects = new Object[softObjects.size()];
    for (int i = 0; i < objects.length; i++) {
      objects[i] = softObjects.get(i).getObject().get();
    }
    return objects;
  }

  /**
   * Transforms an array of object references into an array of object soft references.
   *
   * @param objects The object references.
   * @return The soft reference array.
   */
  @SuppressWarnings("unchecked")
  public static List<SoftObjectReference> toSoftReferences(final Object[] objects) {
    final ArrayList<SoftObjectReference> softObjects = Lists.newArrayList();
    for (int i = 0; i < objects.length; i++) {
      softObjects.add(ImmutableSoftObjectReference.of(new SoftReference<>(objects[i])));
    }
    return softObjects;
  }

}
