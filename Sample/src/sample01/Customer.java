package sample01;

import java.util.Enumeration;
import java.util.Vector;

// Customer(顧客） クラスは店で取り扱う顧客表します。
public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();
    
    public Customer( String name ) {
        _name = name;
    }
    
    public void addRental( Rental arg ) {
        _rentals.addElement( arg );
    }
    
    public String getName() {
        return _name;
    }
    
    public String statement() {
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "_n";
        while( rentals.hasMoreElements() ) {
            Rental each = (Rental) rentals.nextElement();
            // この貸し出しに関する数値の表示
            result += "_t" + each.getMovie().getTitle() + "_t" + String.valueOf( each.getCharge() ) + "_t";
        }
        
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf( getTotalAmount() ) + "_n";
        result += "You earned " + String.valueOf( getTotalFrequentRenterPoints() ) + " frequent renter points";
        
        return result;
    }

    public double getTotalAmount() {
        double result = 0;
        
        Enumeration<Rental> rentals = _rentals.elements();
        while( rentals.hasMoreElements() ) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }
    
    public int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while( rentals.hasMoreElements() ) {
            Rental each = (Rental) rentals.nextElement();
            result = each.getFrequentRenterPoints();
        }
        return result;
    }
}
