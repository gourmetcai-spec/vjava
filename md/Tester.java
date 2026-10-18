public class Tester {

  public void runTests() {

    myMath myTest = new myMath();

    System.out.print("Name \t\t");
    System.out.print("Expected \t");
    System.out.print("Actual \n");
    System.out.print("-------------------------\n");

    //Test #1
    //5 x 5 = 25
    //Return: true
    int product = myTest.multiple(5,5);
    System.out.print("\nMulti # 1 \t");
    System.out.print("true \t\t");
    if(product == 25) {
      System.out.print(true);
    } else {
      System.out.print(false);
    }
    
    //Test #2
    //4 x 5 != 25
    //Return: false
    product = myTest.multiple(4,5);
    System.out.print("\nMulti # 2\t");
    System.out.print("false \t\t");
    if(product == 25) {
      System.out.print(true);
    } else {
      System.out.print(false);
    }

    
  }
  
}