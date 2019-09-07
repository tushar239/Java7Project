/**
 * @author Tushar Chokshi @ 6/17/17.
 */
public class TestParentChild {
    public static void main(String[] args) {
        Parent parent = new Child();
        System.out.println(parent.getClass());

        Parent parent1 = new Parent() {

        };
        System.out.println(parent1.getClass());

    }
}
