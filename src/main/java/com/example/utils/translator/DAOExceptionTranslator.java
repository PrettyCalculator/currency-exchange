package com.example.utils.translator;

import com.example.exc.dao.*;
import com.example.exc.service.EntityAlreadyExistsException;
import com.example.exc.service.EntityNotFoundException;
import com.example.exc.service.ServiceException;

import java.util.Map;
import java.util.function.BiFunction;

public final class DAOExceptionTranslator {

    private DAOExceptionTranslator() {}

    private static final Map<Class<? extends DaoException>, BiFunction<String, DaoException, ServiceException>> daoExceptionMap = Map.of(
            UniqueViolationException.class, EntityAlreadyExistsException::new,
            ForeignKeyViolationException.class, EntityNotFoundException::new
    );

    public static ServiceException translate(String message, DaoException cause) {
        BiFunction<String, DaoException, ServiceException> factory = daoExceptionMap.get(cause.getClass());
        if (factory != null) {
            return factory.apply(message, cause);
        }
        return new ServiceException(message, cause);
    }
}
