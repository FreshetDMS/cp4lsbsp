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

import java.util.Map;
import java.util.Set;

public class Item<N extends Number> implements Comparable<Item<N>> {
  private final GenericMaths<N> mathUtil = new GenericMaths<>();
  private final Map<String, N> requirement;
  private double size = 0;
  private int topic;
  private int partition;
  private int replicaId;

  public Item(Map<String, N> requirement, int topic, int partition, int replicaId) {
    this.requirement = requirement;
    this.topic = topic;
    this.partition = partition;
    this.replicaId = replicaId;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public int getTopic() {
    return topic;
  }

  public void setTopic(int topic) {
    this.topic = topic;
  }

  public int getPartition() {
    return partition;
  }

  public void setPartition(int partition) {
    this.partition = partition;
  }

  public int getReplicaId() {
    return replicaId;
  }

  public void setReplicaId(int replicaId) {
    this.replicaId = replicaId;
  }

  public Map<String, N> getRequirement() {
    return requirement;
  }

  public Set<String> dimensions() {
    return requirement.keySet();
  }

  public N requirement(String dimension) {
    return requirement.<N>get(dimension);
  }

  @Override
  public int compareTo(Item<N> o) {
    return Double.compare(this.size, o.size);
  }

  @Override
  public String toString() {
    return "Item{" +
        "mathUtil=" + mathUtil +
        ", requirement=" + requirement +
        ", size=" + size +
        '}';
  }
}
