package ddd.domain.shared;

import java.io.Serializable;

/**
 * 値オブジェクトインターフェース
 */
public interface ValueObject<T> extends Serializable {

    boolean sameValueAs(T other);

}
