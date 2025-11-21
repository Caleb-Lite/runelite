package net.runelite.client.plugins.pluginhub.com.salverrs.GEFilters.Filters.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchState {
    private String title;
    private String searchValue;

    public SearchState(String title, String searchValue)
    {
        this.title = title;
        this.searchValue = searchValue;
    }
}
