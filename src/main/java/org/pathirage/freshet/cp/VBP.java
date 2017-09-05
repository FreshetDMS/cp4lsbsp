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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VBP<N extends Number> {

  private final List<Item<N>> items;

  private final Map<String, N> binCapacity;

  public VBP(List<Item<N>> items, Map<String, N> binCapacity) {
    this.items = items;
    this.binCapacity = binCapacity;
  }

  public int solve() {
    List<Bin<N>> bins = createBins(items.size(), binCapacity);

    SizeUtility.updateBinSizeWithRemainingCapacityBasedNormalization(bins);
    SizeUtility.updateItemSizeWithRemainingCapacityBasedNormalization(items, bins);
    while (!items.isEmpty()) {
      Item<N> item = Collections.max(items);
      items.remove(item);

      Collections.sort(bins);
      boolean packed = false;
      for (Bin<N> bin : bins) {
        if (bin.add(item)) {
          packed = true;
          break;
        } else {
          packed = false;
        }
      }

      if (!packed) {
        throw new RuntimeException("Cannot pack item " + item);
      }
    }

    int binCount = 0;

    for (Bin<N> b : bins) {
      if (!b.getItems().isEmpty()) {
        binCount++;
      }
    }

    return binCount;
  }

  private List<Bin<N>> createBins(int binCount, Map<String, N> binCapacity) {
    List<Bin<N>> bins = new ArrayList<>();
    for (int i = 0; i < binCount; i++) {
      bins.add(new Bin<>(binCapacity));
    }

    return bins;
  }

  public static void main(String[] args) {

  }
}
