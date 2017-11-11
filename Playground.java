public class  playground{
    public static void main(String[] args) {
      try {
      new Square("a1");
    } catch (InvalidSquareException e) {
      fail("InvalidSquareException for valid square: " + e.getMessage());
    }
    try {
      String invalidSquare = "a9";
      new Square(invalidSquare);
      fail("No InvalidSquareException for invalid square: " + invalidSquare);
    } catch (InvalidSquareException e) {
      // Success
    }
    Square s = new Square("f7");
    assertEquals('f', s.getFile());
    assertEquals('7', s.getRank());
    Square s2 = new Square('e', '4');
    assertEquals("e4", s2.toString());
    }
}
