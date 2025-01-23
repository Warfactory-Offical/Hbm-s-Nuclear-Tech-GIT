package com.hbm.render.amlfrom1710;

public class ModelFormatException extends RuntimeException {

    private static final long serialVersionUID = 2023547503969671835L;

    public ModelFormatException()
    {
        super();
    }

    public ModelFormatException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public ModelFormatException(final String message)
    {
        super(message);
    }

    public ModelFormatException(final Throwable cause)
    {
        super(cause);
    }

}
