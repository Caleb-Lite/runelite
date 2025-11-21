package net.runelite.client.plugins.pluginhub.io.septem150.xeric.util;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.runelite.api.Client;
import net.runelite.api.WorldType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorldUtil {
  private static final Set<WorldType> invalidWorldTypes =
      Set.of(
          WorldType.NOSAVE_MODE,
          WorldType.BETA_WORLD,
          WorldType.FRESH_START_WORLD,
          WorldType.DEADMAN,
          WorldType.PVP_ARENA,
          WorldType.QUEST_SPEEDRUNNING,
          WorldType.SEASONAL,
          WorldType.TOURNAMENT_WORLD);

  public static boolean isValidWorldType(Client client) {
    if (!client.isClientThread()) {
      return false;
    }
    return Sets.intersection(invalidWorldTypes, client.getWorldType()).isEmpty();
  }
}

/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
