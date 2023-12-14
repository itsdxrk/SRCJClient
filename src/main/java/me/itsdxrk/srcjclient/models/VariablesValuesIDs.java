package me.itsdxrk.srcjclient.models;

public class VariablesValuesIDs {

    public String varId;
    public String valueId;

    public VariablesValuesIDs(String varId, String valueId) {
        this.varId = varId;
        this.valueId = valueId;
    }
    public String getVarId() {
        return varId;
    }

    public void setVarId() {
        this.varId = varId;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId() {
        this.valueId = valueId;
    }

    @Override
    public String toString() {
        return varId;
    }
}
