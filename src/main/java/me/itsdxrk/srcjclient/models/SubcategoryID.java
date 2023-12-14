package me.itsdxrk.srcjclient.models;

public class SubcategoryID {

    public String subcategoryName;
    public String subcategoryId;
    public String varId;

    public SubcategoryID(String subcategoryName, String subcategoryId, String varId) {
        this.subcategoryName = subcategoryName;
        this.subcategoryId = subcategoryId;
        this.varId = varId;
    }

    public String getName() {
        return subcategoryName;
    }

    public void setName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getId() {
        return subcategoryId;
    }

    public void setId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getVarId() {
        return this.varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    @Override
    public String toString() {
        return subcategoryName;
    }
}
