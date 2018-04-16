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

public class CorrespondenciaNifException extends Exception {

    private String message;

    public CorrespondenciaNifException() {
        super();
    }

    public CorrespondenciaNifException(String message) {
        super(message);
    }

    public CorrespondenciaNifException(String message, Throwable cause) {

        super(message, cause);
    }


}