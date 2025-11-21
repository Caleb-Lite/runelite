package net.runelite.client.plugins.pluginhub.io.septem150.xeric.util;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtil {
  public static final String ID_GROUP = "id";
  public static final String NAME_GROUP = "name";
  public static final String COUNT_GROUP = "count";
  public static final Pattern CLOG_REGEX =
      Pattern.compile("New item added to your collection log: (?<" + NAME_GROUP + ">.*)");
  public static final Pattern COMBAT_TASK_REGEX =
      Pattern.compile(
          "CA_ID:(?<"
              + ID_GROUP
              + ">[0-9,]+)\\|Congratulations, you've completed an? \\w+ combat task:"
              + " .+? \\([0-9,]+ points?\\)\\.");
  public static final Pattern DIARY_REGEX =
      Pattern.compile(
          "Well done! You have completed an? \\w+ task in the .* area\\. Your Achievement"
              + " Diary has been updated\\.");
  public static final Pattern KC_REGEX =
      Pattern.compile(
          "Your (?:subdued |completed )?(?<"
              + NAME_GROUP
              + ">.+?) (?:kill |success )?count is:"
              + " (?<"
              + COUNT_GROUP
              + ">[0-9,]+)\\.");
  public static final Pattern DELVE_KC_REGEX =
      Pattern.compile("Deep delves completed: (?<" + COUNT_GROUP + ">[0-9,]+)");
  public static final Pattern CLUE_REGEX =
      Pattern.compile(
          "You have completed (?<"
              + COUNT_GROUP
              + ">[0-9,]+) (?<"
              + NAME_GROUP
              + ">.*) Treasure Trails?\\.");
  public static final Pattern QUEST_REGEX =
      Pattern.compile("Congratulations, you've completed a quest:.*");
}

/*
 * Copyright (c) 2019, Alexsuperfly <https://github.com/Alexsuperfly>
 * Copyright (c) 2019, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
