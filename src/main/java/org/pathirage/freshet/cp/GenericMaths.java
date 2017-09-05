/**
 * Copyright 2017 Milinda Pathirage
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pathirage.freshet.cp;

public class GenericMaths<T extends Number> {

  @SuppressWarnings({"unchecked"})
  public T add(T t1, T t2) {
    if (t1 instanceof Double) {
      return (T) Double.valueOf(t1.doubleValue() + t2.doubleValue());
    } else if (t1 instanceof Integer) {
      return (T) Integer.valueOf(t1.intValue() + t2.intValue());
    } else if (t1 instanceof Float) {
      return (T) Float.valueOf(t1.floatValue() + t2.floatValue());
    }

    throw new IllegalArgumentException("Unsupported number type " + t1.getClass());
  }

  @SuppressWarnings({"unchecked"})
  public T sub(T t1, T t2) {
    if (t1 instanceof Double) {
      return (T) Double.valueOf(t1.doubleValue() - t2.doubleValue());
    } else if (t1 instanceof Integer) {
      return (T) Integer.valueOf(t1.intValue() - t2.intValue());
    } else if (t1 instanceof Float) {
      return (T) Float.valueOf(t1.floatValue() - t2.floatValue());
    }

    throw new IllegalArgumentException("Unsupported number type " + t1.getClass());
  }

  @SuppressWarnings({"unchecked"})
  public boolean gt(T t1, T t2) {
    if (t1 instanceof Double) {
      return t1.doubleValue() > t2.doubleValue();
    } else if (t1 instanceof Integer) {
      return t1.intValue() > t2.intValue();
    } else if (t1 instanceof Float) {
      return t1.floatValue() > t2.floatValue();
    }

    throw new IllegalArgumentException("Unsupported number type " + t1.getClass());
  }

  @SuppressWarnings({"unchecked"})
  public boolean lt(T t1, T t2) {
    if (t1 instanceof Double) {
      return t1.doubleValue() < t2.doubleValue();
    } else if (t1 instanceof Integer) {
      return t1.intValue() < t2.intValue();
    } else if (t1 instanceof Float) {
      return t1.floatValue() < t2.floatValue();
    }

    throw new IllegalArgumentException("Unsupported number type " + t1.getClass());
  }

  @SuppressWarnings({"unchecked"})
  public boolean eq(T t1, T t2) {
    if (t1 instanceof Double) {
      return t1.doubleValue() == t2.doubleValue();
    } else if (t1 instanceof Integer) {
      return t1.intValue() == t2.intValue();
    } else if (t1 instanceof Float) {
      return t1.floatValue() == t2.floatValue();
    }

    throw new IllegalArgumentException("Unsupported number type " + t1.getClass());
  }
}
