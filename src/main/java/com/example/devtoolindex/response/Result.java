package com.example.devtoolindex.response;

import lombok.Getter;

public class Result {
    @Getter private int arg1;
    @Getter private int arg2;
    @Getter private String op;
    @Getter private int result;

    public Result(int arg1, int arg2, String op, int result) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.op = op;
        this.result = result;
    }

    public Result() {
        super();
    }
}
