package ddd.infrastructure.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import ddd.domain.shared.Entity;

import play.db.jpa.Model;

@MappedSuperclass
public abstract class Models extends Model {
    
    //楽観ロックカラム
    @SuppressWarnings("unused")
    @Version
    @Column(name="optlock")
    private Integer version;
    
    //生成日
    @Column(nullable=false)
    private Date createDate;
    
    //更新日
    private Date modifyDate;
    
    //無効化フラグ
    @Column(nullable=false)
    private int disable = 0;
    
    public Long id() {
        return id;
    }
    
    public Date createDate() {
        return createDate;
    }
    
    public Date modifyDate() {
        return modifyDate;
    }
    
    public boolean isDisable() {
        return disable == 0 ? false : true;
    }
    
//    public void enable() {
//        this.disable = 1;
//        this.save();
//    }
//    
//    public void disable() {
//        this.disable = 0;
//        this.save();
//    }
    
    @SuppressWarnings("unused")
    @PrePersist
    private void prePersist() {
        createDate = new Date();
    }
    
    @SuppressWarnings("unused")
    @PreUpdate
    private void preUpdate() {
        modifyDate = new Date();
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
