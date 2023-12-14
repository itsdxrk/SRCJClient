package me.itsdxrk.srcjclient.models;

public class CategoryID {

    public String categoryName;
    public String categoryId;

    public CategoryID(String categoryName, String categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getName() {
        return categoryName;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return categoryId;
    }

    public void setId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
