package com.spw.elife.common.exception;


import java.util.List;

/**
 * 重复的实体异常.
 *
 * @author lip
 */
public class DuplicatedEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String name;
    private List<? extends Object> entities;

    public DuplicatedEntityException(String name) {
        super("系统存在重复的" + name);
        this.name = name;
    }

    public DuplicatedEntityException(String name, List<? extends Object> entities) {
        super("系统存在重复的" + name + ":" + entities);
        this.name = name;
        this.entities = entities;
    }

    public DuplicatedEntityException(String name, Throwable cause) {
        super("系统存在重复的" + name, cause);
        this.name = name;
    }

    public DuplicatedEntityException(String name, List<? extends Object> entities, Throwable cause) {
        super("系统存在重复的" + name, cause);
        this.name = name;
        this.entities = entities;
    }

    public String getName() {
        return name;
    }

    public List<? extends Object> getEntities() {
        return entities;
    }
}
