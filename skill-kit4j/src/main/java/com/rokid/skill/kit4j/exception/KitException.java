package com.rokid.skill.kit4j.exception;

import com.rokid.skill.kit4j.core.EnumCodeName;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用异常情况
 *
 * @author wuyukai
 */
public class KitException extends RuntimeException {

    private static final long serialVersionUID = -2586492750892355169L;

    @Getter @Setter
    private EnumCodeName code;


    public KitException(EnumCodeName code) {
        super(code.getName());
        this.code = code;
    }

    public KitException(EnumCodeName code, String message) {
        super(message);
        this.code = code;
    }

    public KitException(EnumCodeName code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public KitException(EnumCodeName code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected KitException(EnumCodeName code, String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
