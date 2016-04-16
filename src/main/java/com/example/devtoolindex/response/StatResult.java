package com.example.devtoolindex.response;

import com.example.devtoolindex.db.entity.IPStatEntity;
import lombok.Getter;

public class StatResult {
    @Getter private int count;
    @Getter private IPStatEntity lastVisit;

    public StatResult(int count, IPStatEntity lastVisit) {
        super();
        this.count = count;
        this.lastVisit = lastVisit;
    }

    public StatResult() {
        super();
    }
}
