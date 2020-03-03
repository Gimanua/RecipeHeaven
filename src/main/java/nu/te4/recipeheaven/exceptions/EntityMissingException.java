/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Adrian Klasson
 */
@ApplicationException(rollback = true)
public final class EntityMissingException extends Exception {

    public EntityMissingException(String message) {
        super(message);
    }
    
}
