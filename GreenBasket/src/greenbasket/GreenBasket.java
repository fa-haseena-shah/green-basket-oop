/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package greenbasket;
import ClassPackage.ProductManagerClass;
/**
 *
 * @author Haseena Shah
 */
public class GreenBasket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // simulating some stock updates show inventory monitoring works
        ProductManagerClass poc=new ProductManagerClass();
        poc.reduceStock("P1", 100);
        poc.reduceStock("P2", 250);
        poc.reduceStock("P3", 1450);
        poc.reduceStock("P5", 75);
    }    
}
