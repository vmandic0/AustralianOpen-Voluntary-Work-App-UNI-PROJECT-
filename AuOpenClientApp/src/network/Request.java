/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import java.io.Serializable;

/**
 *
 * @author vuk
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private RequestType type;
    private Object data;

    public Request(RequestType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public Request() {
    }

    

    public RequestType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
