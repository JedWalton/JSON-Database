package client;

import com.google.gson.annotations.Expose;

public class ArgsInput {
    @Expose
    String type;
    @Expose
    int key;
    @Expose
    String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
