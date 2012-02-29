package ddd.domain.shared;

/**
 * エンティティインターフェース
 */
public interface Entity<T> {
    
    boolean sameIdentityAs(T other);

}
