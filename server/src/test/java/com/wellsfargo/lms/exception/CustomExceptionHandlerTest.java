package com.wellsfargo.lms.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class CustomExceptionHandlerTest {
    /**
     * Method under test: {@link CustomExceptionHandler#handleUserSaveException(UserNotFound)}
     */
    @Test
    void testHandleUserSaveException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        ResponseEntity<Map<String, Object>> actualHandleUserSaveExceptionResult = customExceptionHandler
                .handleUserSaveException(new UserNotFound("Not all who wander are lost"));
        assertEquals(2, actualHandleUserSaveExceptionResult.getBody().size());
        assertTrue(actualHandleUserSaveExceptionResult.hasBody());
        assertTrue(actualHandleUserSaveExceptionResult.getHeaders().isEmpty());
        assertEquals(400, actualHandleUserSaveExceptionResult.getStatusCodeValue());
    }

    /**
     * Method under test: {@link CustomExceptionHandler#handleDataNotFoundException(DataNotFound)}
     */
    @Test
    void testHandleDataNotFoundException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        ResponseEntity<Map<String, Object>> actualHandleDataNotFoundExceptionResult = customExceptionHandler
                .handleDataNotFoundException(new DataNotFound("Not all who wander are lost"));
        assertEquals(2, actualHandleDataNotFoundExceptionResult.getBody().size());
        assertTrue(actualHandleDataNotFoundExceptionResult.hasBody());
        assertTrue(actualHandleDataNotFoundExceptionResult.getHeaders().isEmpty());
        assertEquals(404, actualHandleDataNotFoundExceptionResult.getStatusCodeValue());
    }
}

