package net.runelite.client.plugins.pluginhub.com.osrsprofile;

import net.runelite.api.Client;
import net.runelite.client.RuneLite;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class VarExporter {
    @Inject
    private Client client;

    public void export() {
        File file = new File(RuneLite.RUNELITE_DIR, "vars.txt");
        PrintStream ps = null;

        try {
            ps = new PrintStream(file);

            this.exportVars(ps, "varp");
            this.exportVars(ps, "varb");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            ps.close();
        }
    }

    // This is a debug command used to extract all vars of a player to check for values
    private void exportVars(PrintStream ps, String type) {
        ps.println(type);

        Function<Integer, Integer> method = null;
        if (type == "varp") {
            method = client::getVarpValue;
        } else {
            method = client::getVarbitValue;
        }

        for (int i = 0; i < 30000; i++) {
            try {
                ps.println(String.format("%d: %d", i, method.apply(i)));
            } catch (IndexOutOfBoundsException e) {
                // Do nothing
            }
        }
    }
}
