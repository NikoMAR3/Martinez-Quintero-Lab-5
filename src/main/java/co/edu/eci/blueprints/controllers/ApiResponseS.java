package co.edu.eci.blueprints.controllers;

public record ApiResponseS<T>(int code, String message, T data) {}
