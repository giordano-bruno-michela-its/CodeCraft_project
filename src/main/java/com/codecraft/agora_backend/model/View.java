package com.codecraft.agora_backend.model;

/**
 * View class containing nested static classes for different JSON views.
 */
public class View {

    /**
     * JSON view for GET requests.
     */
    public static class GetView {}

    /**
     * JSON view for POST/PUT requests.
     */
    public static class PostView {}

    /**
     * JSON view for nested entities POST/PUT requests.
     */
    public static class SubView {}
}
