package com.example.utils.translator;

import com.example.exc.dao.*;
import com.example.exc.service.EntityAlreadyExistsException;
import com.example.exc.service.EntityNotFoundException;
import com.example.exc.service.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DAOExceptionTranslator {

    private DAOExceptionTranslator() {}

    private static final Map<Class<? extends DaoException>, BiFunction<String, DaoException, ServiceException>> daoExceptionMap;


    static {
        daoExceptionMap = new HashMap<>();
        daoExceptionMap.put(UniqueViolationException.class, EntityAlreadyExistsException::new);
        daoExceptionMap.put(ForeignKeyViolationException.class, EntityNotFoundException::new);
        daoExceptionMap.put(DbConnectionException.class, ServiceException::new);
        daoExceptionMap.put(DbException.class, ServiceException::new);
        daoExceptionMap.put(DaoException.class, ServiceException::new);
    }

    public static ServiceException translate(String message, DaoException cause) {

        BiFunction<String, DaoException, ServiceException> biFunction = daoExceptionMap.get(cause.getClass());
        if (biFunction != null) {
            return biFunction.apply(message, cause);
        }
        return new ServiceException(message, cause);

    }
}
