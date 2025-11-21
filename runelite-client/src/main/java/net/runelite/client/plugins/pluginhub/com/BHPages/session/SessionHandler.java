package net.runelite.client.plugins.pluginhub.com.BHPages.session;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.runelite.client.RuneLite;

import javax.inject.Inject;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SessionHandler
{
    private static final String FILE_NAME = "player_notes.json";
    private final File notesFile;
    private final Gson gson;
    private Map<String, String> playerNotes = new HashMap<>();

    @Inject
    public SessionHandler(Gson gson)
    {
        this.gson = gson;

        File pluginDir = new File(RuneLite.RUNELITE_DIR, "bhpages");
        if (!pluginDir.exists())
        {
            pluginDir.mkdirs();
        }

        this.notesFile = new File(pluginDir, FILE_NAME);
        loadNotes(); // Load on init
    }

    public void loadNotes()
    {
        if (!notesFile.exists()) return;

        try (Reader reader = new InputStreamReader(new FileInputStream(notesFile), StandardCharsets.UTF_8))
        {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> rawNotes = gson.fromJson(reader, type);

            boolean changed = false;
            Map<String, String> cleaned = new HashMap<>();

            for (Map.Entry<String, String> entry : rawNotes.entrySet())
            {
                String rawName = entry.getKey();
                String rawNote = entry.getValue();

                String cleanName = sanitize(rawName);
                String cleanNote = sanitizeNote(rawNote);

                if (cleaned.containsKey(cleanName))
                {
                    String existingNote = cleaned.get(cleanName);

                    // Merge if note is different and not already part of the current one
                    if (!existingNote.equals(cleanNote) && !cleanNote.isEmpty())
                    {
                        if (!existingNote.toLowerCase().contains(cleanNote.toLowerCase()))
                        {
                            String mergedNote = existingNote + " --- " + cleanNote;
                            cleaned.put(cleanName, mergedNote);
                            changed = true;
                        }
                    }
                }
                else
                {
                    cleaned.put(cleanName, cleanNote);
                    if (!cleanName.equals(rawName) || !cleanNote.equals(rawNote))
                    {
                        changed = true;
                    }
                }
            }

            this.playerNotes = cleaned;

            if (changed)
            {
                saveNotes(); // Re-save fixed file
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void saveNotes()
    {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(notesFile), StandardCharsets.UTF_8))
        {
            gson.toJson(playerNotes, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Map<String, String> getAllNotes()
    {
        return new HashMap<>(playerNotes);
    }

    public String getNoteForPlayer(String playerName)
    {
        return playerNotes.getOrDefault(playerName, "");
    }

    private String sanitize(String input)
    {
        if (input == null)
        {
            return "";
        }

        return input
                .replace('\uFFFD', ' ')             // Replace the replacement character with a space
                .replace('\u00A0', ' ')             // Replace non-breaking spaces with regular space
                .replaceAll("[^-A-Za-z0-9_ ]", "")     // Keep "-" char, A-Z, a-z, 0-9, underscore and spaces only
                .replaceAll("\\s{2,}", " ")          // Collapse multiple spaces into a single space
                .trim();
    }

    private String sanitizeNote(String note)
    {
        if (note == null)
        {
            return "";
        }

        return note
                .replace('\u00A0', ' ')   // Replace non-breaking space
                .replaceAll("[\\p{C}&&[^\\n\\t]]", "") // Remove control characters
                //.replaceAll("\\p{C}", "") // Remove control characters
                .trim();
    }


    public void updateNote(String playerName, String note)
    {
        playerName = sanitize(playerName);
        note = sanitizeNote(note);

        if (note.isEmpty())
        {
            playerNotes.remove(playerName);
        }
        else
        {
            playerNotes.put(playerName, note);
        }

        saveNotes();
    }

}

/*
 * Copyright (c) 2018, John Pettenger
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//thanks & credits to hiscores plugin!