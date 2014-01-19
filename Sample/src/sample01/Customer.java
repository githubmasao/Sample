package sample01;

import java.util.Enumeration;
import java.util.Vector;

// Customer(顧客） クラスは店で取り扱う顧客表します。
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();
    
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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "_n";
        while( rentals.hasMoreElements() ) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();
            
            // レンタルポイントを加算
            frequentRenterPoints++;
            
            // 新作を二日以上借りた場合はボーナスポイント
            if( (each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1 ) {
                frequentRenterPoints++;
            }
            
            // この貸し出しに関する数値の表示
            result += "_t" + each.getMovie().getTitle() + "_t" + String.valueOf( each.getCharge() ) + "_t";
            totalAmount += each.getCharge();
        }
        
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf( totalAmount ) + "_n";
        result += "You earned " + String.valueOf( frequentRenterPoints ) + " frequent renter points";
        
        return result;
    }

    private double amountFor( Rental aRental) {
        return aRental.getCharge();
    }
}
