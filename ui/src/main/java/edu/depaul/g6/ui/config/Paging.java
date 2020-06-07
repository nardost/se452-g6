package edu.depaul.g6.ui.config;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Paging {
    public int numPages;      // number of pages
    public int currentPage;   // current page
    public int pageSize; // items per page

    public boolean hasPrevious() { return currentPage != 0; }
    public boolean hasNext()     { return currentPage != numPages - 1; }
}
