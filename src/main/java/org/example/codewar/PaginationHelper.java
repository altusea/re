package org.example.codewar;

import java.util.List;

public class PaginationHelper<I> {

    private final List<I> dataList;
    private final int itemsPerPage;

    private final int itemsCount;
    private final int pagesTotal;

    /**
     * The constructor takes in an array of items and an integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.dataList = collection;
        this.itemsPerPage = itemsPerPage;

        this.itemsCount = collection.size();
        this.pagesTotal = (this.itemsCount + itemsPerPage - 1) / itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return this.itemsCount;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return this.pagesTotal;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= pagesTotal) return -1;
        if (pageIndex < pagesTotal - 1) {
            return itemsPerPage;
        }
        return itemsCount % itemsPerPage;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= itemsCount) return -1;
        return itemIndex / itemsPerPage;
    }
}
