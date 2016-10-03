package com.dreamdream.model.view;

public class RespStruct {

    private boolean succ;
    private String message;
    private Object obj;

    public static class Builder {
        private boolean succ;
        private String message;
        private Object obj;

        public RespStruct build() {
            RespStruct r = new RespStruct();
            r.setSucc(succ);
            r.setMessage(message);
            r.setObj(obj);
            return r;
        }

        public Builder succ(boolean succ) {
            this.succ = succ;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder obj(Object obj) {
            this.obj = obj;
            return this;
        }

    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
