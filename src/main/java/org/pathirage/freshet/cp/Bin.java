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

import java.util.*;

public class Bin<N extends Number> implements Comparable<Bin<N>> {
  private GenericMaths<N> mathUtil = new GenericMaths<>();
  private final Map<String, N> capacity;
  private final Map<String, N> remaining;
  private final List<Item<N>> items;
  private double size = 0;

  public Bin(Map<String, N> capacity) {
    this.capacity = new HashMap<>();
    this.capacity.putAll(capacity);
    this.remaining = new HashMap<>();
    this.remaining.putAll(capacity);
    this.items = new ArrayList<>();
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public N remainning(String dimension) {
    return remaining.get(dimension);
  }

  public boolean isFeasible(Item<N> i) {
    if (i.getRequirement().keySet().size() != capacity.keySet().size()) {
      throw new RuntimeException("Bin and item dimensions do not match.");
    }

    for (String dimension : i.dimensions()) {
      if (remaining.get(dimension) == null) {
        throw new RuntimeException("Bin and item dimensions do not match.");
      }
    }

    for (Item<N> item : items) {
      // replica constraint
      if (item.getPartition() == i.getPartition() && item.getTopic() == i.getTopic()) {
        return false;
      }
    }

    for (String dimension : i.dimensions()) {
      if (mathUtil.gt(i.requirement(dimension), remaining.get(dimension))) {
        return false;
      }
    }

    return true;
  }

  public Set<String> dimensions() {
    return capacity.keySet();
  }

  public boolean add(Item<N> i) {
    if (isFeasible(i)) {
      insert(i);
      return true;
    }

    return false;
  }

  protected void insert(Item<N> i) {
    for (String dimension : i.dimensions()) {
      remaining.put(dimension, mathUtil.sub(remaining.get(dimension), i.requirement(dimension)));
    }

    items.add(i);
  }

  public Map<String, N> predictRemaining(Item<N> i) {
    Map<String, N> prediction = new HashMap<>();

    for (String dimension : i.dimensions()) {
      prediction.put(dimension, mathUtil.sub(remaining.get(dimension), i.requirement(dimension)));
    }

    return prediction;
  }

  public void empty() {
    this.items.clear();
    this.remaining.putAll(this.capacity);
  }

  public Map<String, N> getCapacity() {
    return capacity;
  }

  public Map<String, N> getRemaining() {
    return remaining;
  }

  public List<Item<N>> getItems() {
    return items;
  }

  @Override
  public int compareTo(Bin<N> o) {
    return Double.compare(this.size, o.size);
  }

  @Override
  public String toString() {
    return "Bin{" +
        "mathUtil=" + mathUtil +
        ", capacity=" + capacity +
        ", remaining=" + remaining +
        ", items=" + items +
        '}';
  }
}
