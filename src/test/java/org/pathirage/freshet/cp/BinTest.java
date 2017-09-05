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

public class BinTest {
  private static final Random rand = new Random(System.currentTimeMillis());

  @Test
  public void testCompare() {
    Map<String, Integer> capacity1 = new HashMap<>();
    capacity1.put("d1", 10);
    capacity1.put("d2", 1000);

    Map<String, Integer> capacity2 = new HashMap<>();
    capacity2.put("d1", 20);
    capacity2.put("d2", 1300);

    Bin<Integer> bin1 = new Bin<>(capacity1);
    Bin<Integer> bin2 = new Bin<>(capacity2);

    List<Bin<Integer>> bins = new ArrayList<>();
    bins.add(bin1);
    bins.add(bin2);

    Map<String, Integer> req1 = new HashMap<>();
    req1.put("d1", 1);
    req1.put("d2", 200);

    Map<String, Integer> req2 = new HashMap<>();
    req2.put("d1", 1);
    req2.put("d2", 345);

    Item<Integer> item1 = new Item<>(req1, 1, 1, 1);
    Item<Integer> item2 = new Item<>(req2, 1, 2, 1);

    List<Item<Integer>> items = new ArrayList<>();
    items.add(item1);
    items.add(item2);

    SizeUtility.<Integer>updateBinSizeWithRemainingCapacityBasedNormalization(bins);
    SizeUtility.<Integer>updateItemSizeWithRemainingCapacityBasedNormalization(items, bins);

    Assert.assertTrue(bin1.compareTo(bin2) < 0);
    Assert.assertTrue(item1.compareTo(item2) < 0);
  }
}
