package converter;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     * <p>
     * @param number A string that represents either a Elbonian or Arabic number.
     *
     * @throws MalformedNumberException  Thrown if the value is an Elbonian number that does not conform
     *                                   to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     *                                   in the Elbonian number system.
     */

    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string
        try {
            NumisValid(number);
        }
        catch (NumberFormatException e){
            ElbonianisValid(number);
        }
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        try{
             return Integer.parseInt(this.number);
        }
        catch (NumberFormatException e){
            int no = 0;
            for (int i = 0; i < this.number.length(); ++i){
                char numberChar = this.number.charAt(i);
                switch (numberChar){
                    case 'M':
                        no = no + 1000;
                        break;
                    case 'E':
                        no = no + 500;
                        break;
                    case 'D':
                        no = no + 300;
                        break;
                    case 'C':
                        no = no + 100;
                        break;
                    case 'Z':
                        no = no + 50;
                        break;
                    case 'Y':
                        no = no + 30;
                        break;
                    case 'X':
                        no = no + 10;
                        break;
                    case 'K':
                        no =  no + 5;
                        break;
                    case 'J':
                        no = no + 3;
                        break;
                    case 'I':
                        no = no + 1;
                        break;
                }
            }
            return no;
        }
    }

    private void NumisValid(String str) throws ValueOutOfBoundsException{
        int newNo = Integer.parseInt(str);
        if ((newNo <= 0) || (newNo >= 4998)){
            throw new ValueOutOfBoundsException("The number" + newNo + "is not Valid. Please use a Valid number between 0 and 4998");
        }
    }

    private void ElbonianisValid(String string) throws MalformedNumberException{
        char[] newcharArray = string.toCharArray();
        for (Character S : newcharArray){
            if(!(S == 'M' || S  == 'E' || S == 'D' || S == 'C' || S == 'Z' || S == 'Y' || S == 'X' || S == 'K' || S == 'J' || S == 'I')){
                throw new MalformedNumberException("The Elbonian Number " + S + "is not valid !");
            }
        }
        NumExcept1(string);
        NumExcept2(string);
        NumExcept3(string);
        NumExcept4(string);
        NumExcept5(string);
        NumExcept6(string);
    }

    private void NumExcept1(String string) throws MalformedNumberException{
        String betterString = string.trim();
        int M = betterString.length() - betterString.replace("M", "").length();
        int C = betterString.length() - betterString.replace("C", "").length();
        int X = betterString.length() - betterString.replace("X", "").length();
        int I = betterString.length() - betterString.replace("I", "").length();
        if( M > 2 || C > 2  || X > 2 || I > 2){ throw new MalformedNumberException("The Elbonian Number " + betterString + " has more than 2 instances of M,C,X,I");}
    }

    private void NumExcept2(String string) throws MalformedNumberException{
        String betterString = string.trim();
        int D = betterString.length() - betterString.replace("D", "").length();
        int E = betterString.length() - betterString.replace("E", "").length();
        int Y = betterString.length() - betterString.replace("Y", "").length();
        int Z = betterString.length() - betterString.replace("Z", "").length();
        int J = betterString.length() - betterString.replace("J", "").length();
        int K = betterString.length() - betterString.replace("K", "").length();
        if( D > 1 || E > 1 || Y > 1 || Z > 1 || J > 1 || K > 1){ throw new MalformedNumberException("The Elbonian Number " + string + " has more than 1 instances of D,E,Y,Z,J,K");}
    }

    private void NumExcept3(String string) throws MalformedNumberException{
        String betterString = string.trim();
        int D = betterString.length() - betterString.replace("D", "").length();
        int C = betterString.length() - betterString.replace("C", "").length();
        if((D == 1) && (C > 1)){
            throw new MalformedNumberException("The Elbonian Number " + betterString + " has more than one C even though D has already appeared once");
        }
    }

    private void NumExcept4(String string) throws MalformedNumberException{
        String betterString = string.trim();
        int Y = betterString.length() - betterString.replace("Y", "").length();
        int X = betterString.length() - betterString.replace("X", "").length();
        if((Y == 1) && (X > 1)){
            throw new MalformedNumberException("The Elbonian Number " + betterString + " has more than one X even though Y has already appeared once");
        }
    }

    private void NumExcept5(String string) throws MalformedNumberException{
        String betterString = string.trim();
        int J = betterString.length() - betterString.replace("J", "").length();
        int I = betterString.length() - betterString.replace("I", "").length();
        if((J == 1) && (I > 1)){
            throw new MalformedNumberException("The Elbonian Number " + betterString + " has more than one I even though J has already appeared once");
        }
    }

    private void NumExcept6(String string) throws MalformedNumberException {
        String betterString = string.trim();
        char[] c = betterString.toCharArray();
        for (int i = 0; i < betterString.length(); ++i) {
            if (c[i] == 'I' && (c[i+1] == 'J' || c[i+1] == 'K' || c[i+1] == 'X' || c[i+1] == 'Y' || c[i+1] == 'Z' || c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'J' && (c[i+1] == 'K' || c[i+1] == 'X' || c[i+1] == 'Y' || c[i+1] == 'Z' || c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'K' && (c[i+1] == 'X' || c[i+1] == 'Y' || c[i+1] == 'Z' || c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'X' && (c[i+1] == 'Y' || c[i+1] == 'Z' || c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'Y' && (c[i+1] == 'Z' || c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'Z' && (c[i+1] == 'C' || c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'C' && (c[i+1] == 'D' || c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'D' && (c[i+1] == 'E' || c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            if (c[i] == 'E' && (c[i+1] == 'M')) {
                throw new MalformedNumberException("The Elbonian Number " + betterString + " Numerals are not in ascending order");
            }
            else{
                return;
            }
        }
    }


    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */

}
