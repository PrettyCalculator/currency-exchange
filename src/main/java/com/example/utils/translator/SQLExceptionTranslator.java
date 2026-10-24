package com.example.utils.translator;

import com.example.exc.dao.DaoException;
import com.example.exc.dao.ForeignKeyViolationException;
import com.example.exc.dao.UniqueViolationException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class SQLExceptionTranslator {

    private SQLExceptionTranslator() {}

    private static final Map<String, BiFunction<String, Throwable, DaoException>> sqlExceptionMap;


    static {
        sqlExceptionMap = new HashMap<>();
        sqlExceptionMap.put("23505", UniqueViolationException::new);
        sqlExceptionMap.put("23503", ForeignKeyViolationException::new);
    }

    public static DaoException translate(String message, SQLException cause) {
        BiFunction<String, Throwable, DaoException> biFunction =
                sqlExceptionMap.get(cause.getSQLState());

        if (biFunction != null) {
            return biFunction.apply(message, cause);
        }
        return new DaoException(message, cause);

    }
}
