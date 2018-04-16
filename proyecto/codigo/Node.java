public class Node{
    Node child1, child2, parent;
    AABBrect aabb;
    int height;

    Node(AABBrect aabb){
        this.aabb = aabb;
        if (aabb.node != null)
            throw new RuntimeException();
        aabb.node = this;
    }

    boolean isLeaf(){return child1 == null;}
}
