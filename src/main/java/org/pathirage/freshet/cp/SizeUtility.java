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

public class SizeUtility<N extends Number> {
  public static <N extends Number> Map<String, Double> computeTotalRemainingResources(List<Bin<N>> bins) {
    if (bins == null || bins.isEmpty()) {
      return Collections.EMPTY_MAP;
    }

    Set<String> dimensions = bins.get(0).dimensions();

    Map<String, Double> remaining = new HashMap<>();
    for (String dim : dimensions) {
      remaining.put(dim, 0.0);
    }

    for (Bin<N> bin : bins) {
      for (String dim : dimensions) {
        remaining.put(dim, remaining.get(dim) + bin.remainning(dim).doubleValue());
      }
    }

    return remaining;
  }

  public static <N extends Number> void updateBinSizeWithRemainingCapacityBasedNormalization(List<Bin<N>> bins) {
    Map<String, Double> totalRemaining = computeTotalRemainingResources(bins);

    if (totalRemaining.isEmpty()) {
      return;
    }

    for (String dim : totalRemaining.keySet()) {
      totalRemaining.put(dim, totalRemaining.get(dim) == 0 ? 0.0 : 1 / totalRemaining.get(dim));
    }

    Set<String> dimensions = bins.get(0).dimensions();
    for (Bin<N> bin : bins) {
      double size = 0;

      for (String dim : dimensions) {
        size += totalRemaining.get(dim) * bin.remainning(dim).doubleValue();
      }

      bin.setSize(size);
    }
  }

  public static <N extends Number> void updateItemSizeWithRemainingCapacityBasedNormalization(List<Item<N>> items, List<Bin<N>> bins) {
    Map<String, Double> totalRemaining = computeTotalRemainingResources(bins);

    if (totalRemaining.isEmpty()) {
      return;
    }

    for (String dim : totalRemaining.keySet()) {
      totalRemaining.put(dim, totalRemaining.get(dim) == 0 ? 0.0 : 1 / totalRemaining.get(dim));
    }

    Set<String> dimensions = bins.get(0).dimensions();
    for (Item<N> item : items) {
      double size = 0;

      for (String dim : dimensions) {
        size += totalRemaining.get(dim) * item.requirement(dim).doubleValue();
      }

      item.setSize(size);
    }
  }
}
