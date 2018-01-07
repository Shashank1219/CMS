
package bill;

import javafx.beans.property.StringProperty;

/**
 *
 * @author mohammed
 */
public class orderlist {
    private int no;
    private  String pido;
    private  String pnameo;
    private  int priceo;
    private  int qtyo;
    private  double amounto;

    public orderlist(int no, String pido, String pnameo, int priceo, int qtyo, double amounto) {
        this.no = no;
        this.pido = pido;
        this.pnameo = pnameo;
        this.priceo = priceo;
        this.qtyo = qtyo;
        this.amounto = amounto;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return the pido
     */
    public String getPido() {
        return pido;
    }

    /**
     * @param pido the pido to set
     */
    public void setPido(String pido) {
        this.pido = pido;
    }

    /**
     * @return the pnameo
     */
    public String getPnameo() {
        return pnameo;
    }

    /**
     * @param pnameo the pnameo to set
     */
    public void setPnameo(String pnameo) {
        this.pnameo = pnameo;
    }

    /**
     * @return the priceo
     */
    public int getPriceo() {
        return priceo;
    }

    /**
     * @param priceo the priceo to set
     */
    public void setPriceo(int priceo) {
        this.priceo = priceo;
    }

    /**
     * @return the qtyo
     */
    public int getQtyo() {
        return qtyo;
    }

    /**
     * @param qtyo the qtyo to set
     */
    public void setQtyo(int qtyo) {
        this.qtyo = qtyo;
    }

    /**
     * @return the amounto
     */
    public double getAmounto() {
        return amounto;
    }

    /**
     * @param amounto the amounto to set
     */
    public void setAmounto(double amounto) {
        this.amounto = amounto;
    }
    
    
    
}
