package com.example.devtoolindex.response;

import com.example.devtoolindex.db.entity.IPStatEntity;
import com.example.devtoolindex.serializer.CustomDateDeserializer;
import com.example.devtoolindex.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.Date;

public class StatResult {
    @Getter private int count;
    @Getter private IPStatEntityResult lastVisit;

    public StatResult(int count, IPStatEntity ipStatEntity) {
        super();
        this.count = count;
        this.lastVisit = new IPStatEntityResult(ipStatEntity);
    }

    public StatResult() {
        super();
    }

    public class IPStatEntityResult extends IPStatEntity {

        private Date visitDate;

        public IPStatEntityResult(IPStatEntity ipStatEntity) {
            super(ipStatEntity.getIp(), ipStatEntity.getVisitDate(), ipStatEntity.getPath());
            this.visitDate = ipStatEntity.getVisitDate();
        }

        public IPStatEntityResult() {
            super();
        }

        @JsonSerialize(using = CustomDateSerializer.class) @Override public Date getVisitDate() {
            return visitDate;
        }

        @JsonDeserialize(using = CustomDateDeserializer.class) @Override public void setVisitDate(Date date) {
            this.visitDate = date;
        }
    }
}
