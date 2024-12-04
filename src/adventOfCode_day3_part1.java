import java.io.*;

public class adventOfCode_day3_part1 {

    private BufferedReader br;
    private int end;
    private int start;
    private boolean dontSwitch = false;
    public adventOfCode_day3_part1(BufferedReader br){
        this.br = br;
    }

    /**
     * Method to loop through the file
     * @throws IOException for errors
     */
    private int loopThroughFile() throws IOException {
        int totalSum = 0;

        String st;
        while ((st = br.readLine()) != null){

            int index = 0;
            char[] charArray = st.toCharArray();

            for (char character : charArray){

                // check if the character is m -> u -> l -> ( -> 1-3 digit number -> , -> 1-3 digit number
                if (character == 'm' && !dontSwitch){
                    if (nextCharsAreMUL(charArray, index)){
                        this.start = index;

                        int mul = checkForNumbers(charArray, index + 4);
                        if (mul != -1){
                            totalSum += mul;
                        }
                    }
                }

                else if (character == 'd'){
                   if(checkForWords(charArray, index, "don't()")){
                       this.dontSwitch = true;
                   }

                   if (checkForWords(charArray, index, "do()")){
                       this.dontSwitch = false;
                   }
                }

                index++;
            }
        }

        System.out.println("The total sum is: " + totalSum);

        return totalSum;
    }

    /**
     * Method to check for the don't
     * @return yes if it's a don't
     */
    private boolean checkForWords(char[] charArray, int startIndex, String word){
        int wordIndex = 0;
        for (int i = startIndex; i < startIndex + word.length(); i ++){
            if(charArray[i] != word.charAt(wordIndex)){
                return false;
            }
            wordIndex++;
        }
        return true;
    }

    /**
     * Method to see if the next characters are in the correct order
     * @param charArray the char array
     * @param index the current index
     * @return yes if the word starts with "mul("
     */
    private boolean nextCharsAreMUL(char[] charArray, int index){
        String actualString = "mul(";
        int actualStringIndex = 0;
        for (int i = index; i < index + 3; i ++){
            if (!(actualString.charAt(actualStringIndex) == charArray[i])){
                return false;
            }
            actualStringIndex++;
        }
        return true;
    }

    /**
     * Method to check the numbers
     * @param charArray the char array
     * @param start the start index
     * @return the int of the multiplication
     */
    private int checkForNumbers(char[] charArray, int start){
        char delimiter = ',';
        char delimiter1 = ')';
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        int ammountToGoToNext = 0;
        int magicInt = 0;
//        int ammountToGoToNext1 = 0;

        for (int i = start; i < start + 4; i++){
            if (charArray[i] == delimiter){
                // we have found a comma,
                break;
            }
            else if (!Character.isDigit(charArray[i])){
                System.out.println("This is in i" + charArray[i]);
                return -1;
            }
            else {
                num1.append(charArray[i]);
            }
            ammountToGoToNext++;
        }
//        System.out.println("This is the ammount to go next " + ammountToGoToNext);
//        System.out.println("THis is the char at that pos: " + charArray[start + ammountToGoToNext]);

        int ammountToGoToNext1 = ammountToGoToNext;

        for (int j = start + ammountToGoToNext + 1; j < start + ammountToGoToNext + 5; j ++){
            if (charArray[j] == delimiter1){
                magicInt = j + 1;
                // we have found a comma,
                break;
            }
            else if (!Character.isDigit(charArray[j])){
                System.out.println( "THis is in j " + charArray[j]);
                return -1;
            }
            else {
                num2.append(charArray[j]);
            }
            ammountToGoToNext1 ++;
        }

        if (checkSizeConstraints(num1) && checkSizeConstraints(num2)){
            this.end = magicInt;
            return mul(Integer.parseInt(num1.toString()), Integer.parseInt(num2.toString()));
        }

        return -1;
    }


    /**
     * Method to multiply two numbers together
     * @param a number 1
     * @param b number 2
     * @return the multiplied a,b
     */
    private int mul(int a, int b){
        return a * b;
    }

    /**
     * Method to check the size constraints
     * @param stringBuilder the string builder
     * @return yes or no
     */
    private boolean checkSizeConstraints(StringBuilder stringBuilder){
        if (stringBuilder.length() <= 3 && stringBuilder.length() > 0){
            return true;
        }
        return false;
    }

    /**
     * Method to print out instances found of the mul(a,b)
     * Where a and b are integers
     * @param charArray the char array
     */
    private void printOutInstancesOfMUL(char[] charArray){
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("Start: " + this.start + "End: " + this.end);
        for (int i = this.start; i < this.end; i++){
            stringBuilder.append(charArray[i]);
        }

        System.out.println("This is the right ans: " + stringBuilder.toString());
    }







    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\19365\\OneDrive\\Documents\\GitHub\\AdventOfCodeDay3\\src\\input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        adventOfCode_day3_part1 adventOfCode3 = new adventOfCode_day3_part1(br);

        adventOfCode3.loopThroughFile();

    }
}
