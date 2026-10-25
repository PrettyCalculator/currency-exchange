package com.example.utils.translator;

import com.example.exc.service.EntityAlreadyExistsException;
import com.example.exc.service.EntityNotFoundException;
import com.example.exc.service.ServiceException;
import com.example.exc.service.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class ServiceExceptionHandler {

    private ServiceExceptionHandler() {}

    private static final Map<Class<? extends ServiceException >, Integer> serviceExceptionMap;
    // почему тут нельзя вместо Class<..> писать просто "? extends ServiceException"?
    static {
        serviceExceptionMap = new HashMap<>();
        serviceExceptionMap.put(EntityAlreadyExistsException.class, 409);
        serviceExceptionMap.put(EntityNotFoundException.class, 404);
        serviceExceptionMap.put(ValidationException.class, 400);
        serviceExceptionMap.put(ServiceException.class, 500);
    }

    public static int getCode(ServiceException e) {
        return serviceExceptionMap.get(e.getClass());

    }



}
