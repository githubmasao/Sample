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
            
            thisAmount = amountFor(each);
            
            // レンタルポイントを加算
            frequentRenterPoints++;
            
            // 新作を二日以上借りた場合はボーナスポイント
            if( (each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1 ) {
                frequentRenterPoints++;
            }
            
            // この貸し出しに関する数値の表示
            result += "_t" + each.getMovie().getTitle() + "_t" + String.valueOf( thisAmount ) + "_t";
            totalAmount += thisAmount;
        }
        
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf( totalAmount ) + "_n";
        result += "You earned " + String.valueOf( frequentRenterPoints ) + " frequent renter points";
        
        return result;
    }

    private double amountFor( Rental each) {
        double thisAmount = 0;
        // 一行毎に金額を計算
        switch( each.getMovie().getPriceCode() ) {
            case Movie.REGULAR:
                thisAmount += 2;
                if( each.getDaysRented() > 2 ) {
                    thisAmount += ( each.getDaysRented() - 2 ) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if( each.getDaysRented() > 3 ) {
                    thisAmount += ( each.getDaysRented() -3 ) * 1.5;
                }
                break;
        }
        return thisAmount;
    }
}
