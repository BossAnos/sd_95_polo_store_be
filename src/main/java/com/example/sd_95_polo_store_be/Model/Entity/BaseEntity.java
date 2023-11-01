package com.example.sd_95_polo_store.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class BaseEntity<T> {
    @JsonProperty("created_date")
    OffsetDateTime createdAt;

    @JsonProperty("updated_date")
    OffsetDateTime updatedAt;

    protected abstract T self();

    public T setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return self();
    }

    public T setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return self();
    }

}
