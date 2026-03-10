public class PrintIndexed {
   /**
     * Prints each character of a given string followed by the reverse of its index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
   public static void printIndexed(String s) {
      // TODO: Fill in this function
      int string_length = s.length();
      for (int i = 0; i <= string_length - 1; i++){
         IO.print(s.charAt(i));
         IO.print(string_length-1-i);
      }
   }

   public static void main(String[] args) {
      printIndexed("hello");
      IO.println();
      printIndexed("cat"); // should print c2a1t0
   }
}