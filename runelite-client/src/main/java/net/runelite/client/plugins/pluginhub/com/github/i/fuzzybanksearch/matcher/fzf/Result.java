package net.runelite.client.plugins.pluginhub.com.github.i.fuzzybanksearch.matcher.fzf;

import java.util.Arrays;

public class Result {

    public static Result empty(final String text, int itemIndex) {
        return new Result(text, 0, 0, 0, null, itemIndex);
    }

    public static Result noMatch(final String text, int itemIndex) {
        return new Result(text, -1, -1, 0, null, itemIndex);
    }

    private final String text;
    private final int start;
    private final int end;
    private final int score;
    private final int[] positions;
    private final int itemIndex;

    public Result(String text, int start, int end, int score, int[] positions, int itemIndex) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.score = score;
        this.positions = positions;
        this.itemIndex = itemIndex;
    }

    public String getText() {
        return text;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getScore() {
        return score;
    }

    public int[] getPositions() {
        return positions;
    }

    public boolean isMatch() {
        return start != -1 && end != -1;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    @Override
    public String toString() {
        return "Result{"
               + "text='" + text + '\''
               + ", start=" + start
               + ", end=" + end
               + ", score=" + score
               + ", positions=" + Arrays.toString(positions)
               + ", itemIndex=" + itemIndex
               + '}';
    }
}
