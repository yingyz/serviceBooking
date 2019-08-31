package com.cpp.servicebooking.exceptions.Response;

public class DatabaseNotFoundExceptionResponse {

    private String DatabaseNotFound;

    public DatabaseNotFoundExceptionResponse(String databaseNotFound) {
        DatabaseNotFound = databaseNotFound;
    }

    public String getDatabaseNotFound() {
        return DatabaseNotFound;
    }

    public void setDatabaseNotFound(String databaseNotFound) {
        DatabaseNotFound = databaseNotFound;
    }
}
