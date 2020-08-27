/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Viotti
 */
public class EmailException extends RuntimeException {

    public EmailException(String string) {
        super(string);
    }
    
}
