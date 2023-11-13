package com.taller.Entity;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;

@Document
public abstract class Auditoria {
    @Id
    private String id;

    @Field
    private Boolean state;

    @Schema(description = "Fecha de creación del dato", example = "")
    @Field
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de actualización del dato", example = "")
    @Field
    private LocalDateTime updatedAt;

    @Schema(description = "Usuario cración", example = "")
    @Field
    private Long createdUser;

    @Schema(description = "Usuario modificación", example = "")
    @Field
    private Long updatedUser;

    @Schema(description = "Usuarios eliminación", example = "")
    @Field
    private Long deletedUser;

    @Schema(description = "Fecha de eliminación del dato", example = "")
    @Field
    private LocalDateTime deletedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * @return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the deletedAt
     */
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    /**
     * @param deletedAt the deletedAt to set
     */
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
