import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void buscar() {
        Association a=new Association("manzana","apple");
        Association h=new Association("jugo","juice");
        BinaryTree<Association> arbol=new BinaryTree<>(a);
        BinaryTree<Association> arbol2=new BinaryTree<>(h);
        arbol.agregar(arbol2,arbol);
        assertEquals("apple",arbol.buscar("manzana"));

    }
}