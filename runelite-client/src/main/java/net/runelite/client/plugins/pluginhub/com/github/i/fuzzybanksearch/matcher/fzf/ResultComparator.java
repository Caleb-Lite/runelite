package net.runelite.client.plugins.pluginhub.com.github.i.fuzzybanksearch.matcher.fzf;



import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {

    private final OrderBy orderBy;

    public ResultComparator(final OrderBy orderBy) {

        this.orderBy = orderBy;
    }

    @Override
    public int compare(Result r1, Result r2) {
        if (orderBy == OrderBy.SCORE) {
            return Integer.compare(r2.getScore(), r1.getScore());
        }
        return Integer.compare(r1.getText().trim().length(), r2.getText().trim().length());
    }
}
