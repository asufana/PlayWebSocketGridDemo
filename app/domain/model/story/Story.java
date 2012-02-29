package domain.model.story;

import javax.persistence.Column;

import org.apache.commons.lang.Validate;
import org.apache.commons.validator.EmailValidator;

import ddd.domain.shared.Entity;
import ddd.infrastructure.persistence.Models;

/**
 * ストーリーエンティティ
 */
@javax.persistence.Entity
public class Story extends Models implements Entity<Story> {
    
    //タイトル
    @Column(nullable=false)
    private String title;
    
    //着手フラグ
    @Column(nullable=false)
    private boolean isCurrent;
    
    //コンストラクタ
    public Story(final String title, final boolean isCurrent) {
        Validate.notNull(title);
        Validate.notNull(isCurrent);
        this.title = title;
        this.isCurrent = isCurrent;
    }
    
    public String title() {
        return title;
    }
    
    public boolean isCurrent() {
        return isCurrent;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Story other = (Story) object;
        return sameIdentityAs(other);
    }

    @Override
    public boolean sameIdentityAs(final Story other) {
        return other != null && this.id.equals(other.id);
    }
}
