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
package org.pathirage.freshet.cb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bin implements Comparable<Bin>{
  private final int capacity;
  private final List<Item> items = new ArrayList<>();
  private int remaining;

  public Bin(int capacity) {
    this.capacity = capacity;
  }

  public int getRemaining() {
    return remaining;
  }

  public int getCapacity() {
    return capacity;
  }

  public void addItem(Item item){
    items.add(item);
  }

  public void removeItem(Item item){
    items.remove(item);
  }

  public Item getBiggestItem(){
    items.sort(new Comparator<Item>() {
      @Override
      public int compare(Item o1, Item o2) {
        return Integer.compare(o1.getSize(), o2.getSize());
      }
    });

    return items.get(0);
  }

  @Override
  public int compareTo(Bin o) {
    return Integer.compare(this.remaining, o.remaining);
  }
}
