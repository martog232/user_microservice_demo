package com.example.user_microservice_demo.util.constants;

public class GlobalConstants {

    //Globals
    public static final String SPRING = "spring";

    // EXCEPTION MESSAGES
    public static final String USER_NOT_FOUND_EXCEPTION_MSG = "User with id %d not found";
    public static final String COUNTRY_NOT_FOUND_EXCEPTION_MSG = "Country with code %s does not exist";


    // LOGGING MESSAGES
    public static final String CREATING_USER_LOG_MSG = "Creating user with name: {}";
    public static final String USER_CREATED_SUCCESSFULLY_LOG_MSG = "User created successfully with ID: {}";
    public static final String USER_FOR_DELETE_NOT_FOUND_LOG_MSG = "Attempted to delete non-existent user with ID: {}";
    public static final String USER_DELETED_LOG_MSG = "User deleted successfully with ID: {}";
    public static final String FAILED_CAR_FETCH_LOG_MSG = "Failed to fetch cars for user ID {}: {}";


    // URI CONSTANTS
    public static final String CARS_BY_OWNER_FETCH_URI = "%s/owner/%d";


    //PRODUCER_TOPICS
    public static final String TOPIC_USER_DELETION = "user-deletion-topic";

}
