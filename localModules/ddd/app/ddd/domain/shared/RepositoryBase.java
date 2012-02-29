package ddd.domain.shared;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import play.db.jpa.GenericModel;
import play.utils.Java;
import ddd.infrastructure.persistence.Models;

/**
 * リポジトリパターン抽象基底クラス
 */
public abstract class RepositoryBase<T extends Models> {

    private Class<T> modelClass;

    public RepositoryBase() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        modelClass = (Class<T>) superclass.getActualTypeArguments()[0];
    }

    public T store(final T object) {
        return object.save();
    }
    
    public void delete(final T object) {
        object.delete();
    }

    public T find(final T object) {
        return GenericModel.findById(object.getId());
    }

    public T findFirst(final String query, final Object... params) {
        try {
            GenericModel.JPAQuery result = (GenericModel.JPAQuery) Java.invokeStatic(modelClass, "find", query, params);
            return result.first();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> findAll() {
        try {
            GenericModel.JPAQuery result = (GenericModel.JPAQuery) Java.invokeStatic(modelClass, "all");
            return result.fetch();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    
    public List<T> findAll(final String query, final Object... params) {
        try {
            GenericModel.JPAQuery result = (GenericModel.JPAQuery) Java.invokeStatic(modelClass, "find", query, params);
            return result.fetch();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
