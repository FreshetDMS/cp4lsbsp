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

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class VBPTest {
  Random random = new Random(System.currentTimeMillis());

  @Test
  public void testBinPacker() {
    Map<String, Integer> capacity1 = new HashMap<>();
    capacity1.put("d1", 20);
    capacity1.put("d2", 1000);
    capacity1.put("d3", 200);
    capacity1.put("d4", 100);
    capacity1.put("d5", 2000);


    List<Item<Integer>> items = randomItems(capacity1, 3, 100, 20);
    System.out.println("Items: " + items.size());
    VBP<Integer> vbp = new VBP<>(items, capacity1);
    long start = System.currentTimeMillis();
    int optimalBinCount = vbp.solve();
    System.out.println("Elapsed: " + (System.currentTimeMillis() - start));

    Assert.assertTrue(optimalBinCount > 0);
    System.out.println("Optimal bin count: " + optimalBinCount);
  }

  private List<Item<Integer>> randomItems(Map<String, Integer> dimensionLimits, int replication, int topics, int lbPartitionsPerTopic) {
    List<Item<Integer>> items = new ArrayList<>();
    for (int t = 0; t < topics; t++) {
      int partitions = lbPartitionsPerTopic + random.nextInt(10) * 10;
      for (int p = 0; p < partitions; p++) {
        Map<String, Integer> reqs = new HashMap<>();
        for (String dim : dimensionLimits.keySet()) {
          reqs.put(dim, Math.round(dimensionLimits.get(dim) * getRandomFactor()));
        }

        for (int j = 0; j < replication; j++) {
          items.add(new Item<Integer>(reqs, t, p, j));
        }
      }
    }

    return items;
  }

  private float getRandomFactor() {
    Float r = random.nextFloat();

    return r > 0.7f ? r - 0.3f : r;
  }
}
