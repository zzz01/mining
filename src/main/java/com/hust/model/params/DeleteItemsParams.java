package com.hust.model.params;

public class DeleteItemsParams {
    private String type;
    private int currentSet;
    private int[] indexSet;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int[] getIndexSet() {
        return indexSet;
    }

    public void setIndexSet(int[] indexSet) {
        this.indexSet = indexSet;
    }

}
