/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.exceptions;

/**
 *
 * @author aines
 */
public class ServiceException extends Exception{

    private String message;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {

        super(message, cause);
    }


}
