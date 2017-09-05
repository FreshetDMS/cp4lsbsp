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
import java.util.List;

public class ReBalancingTool {
  private final List<Bin> bins = new ArrayList<>();

  public ReBalancingTool(List<Bin> bins){
    this.bins.addAll(bins);
  }

  public void rebalance(){

  }

  public List<Bin> getBins() {
    return bins;
  }
}
