/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.excecoes;

/**
 *
 * @author Jorge
 */
public class BDexception extends Exception {

    /**
     * Creates a new instance of <code>BDexception</code> without detail
     * message.
     */
    public BDexception() {
    }

    /**
     * Constructs an instance of <code>BDexception</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BDexception(String msg) {
        super(msg);
    }
}
