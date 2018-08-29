package controller.crypter;

import model.entity.Entity;

public class SecurityEntity<T extends Entity> {
    private int fakeId;

    T entity;

    public SecurityEntity(int fakeId, T entity) {
        this.fakeId = fakeId;
        this.entity = entity;
    }

    public SecurityEntity(T entity) {
        this.fakeId = entity.hashCode();
        this.entity = entity;
    }

    public int getFakeId() {
        return fakeId;
    }

    public void setFakeId(int fakeId) {
        this.fakeId = fakeId;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
