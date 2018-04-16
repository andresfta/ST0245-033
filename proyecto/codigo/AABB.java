import java.util.Iterator;
import java.util.Stack;
public class AABB<T extends AABBrect>
{
    private Node root = null;

    public void insert(T e)
    {
        Node leaf = new Node(e);
        if (root == null)
        {
            root = leaf;
            return;
        }

        // Find the best sibling for this node
        Node node = root;
        while (node.isLeaf() == false)
        {
            Node child1 = node.child1;
            Node child2 = node.child2;

            double area = node.aabb.getPerimeter();

            AABBrect combinedAABB = node.aabb.combine(e);
            double combinedArea = combinedAABB.getPerimeter();

            // Cost of creating a new parent for this node and the new leaf
            double cost = 2.0f * combinedArea;

            // Minimum cost of pushing the leaf further down the tree
            double inheritanceCost = 2.0f * (combinedArea - area);

            // Cost of descending into child1
            double cost1;
            if (child1.isLeaf())
            {
                AABBrect aabb = e.combine(child1.aabb);
                cost1 = aabb.getPerimeter() + inheritanceCost;
            } else
            {
                AABBrect aabb = e.combine(child1.aabb);
                double oldArea = child1.aabb.getPerimeter();
                double newArea = aabb.getPerimeter();
                cost1 = (newArea - oldArea) + inheritanceCost;
            }

            // Cost of descending into child2
            double cost2;
            if (child2.isLeaf())
            {
                AABBrect aabb = e.combine(child2.aabb);
                cost2 = aabb.getPerimeter() + inheritanceCost;
            } else
            {
                AABBrect aabb = e.combine(child2.aabb);
                double oldArea = child2.aabb.getPerimeter();
                double newArea = aabb.getPerimeter();
                cost2 = (newArea - oldArea) + inheritanceCost;
            }

            // Descend according to the minimum cost.
            if (cost < cost1 && cost < cost2)
            {
                break;
            }

            // Descend
            if (cost1 < cost2)
            {
                node = child1;
            } else
            {
                node = child2;
            }
        }

        Node sibling = node;

        // Create a new parent.
        Node oldParent = sibling.parent;
        Node newParent = new Node(e.combine(sibling.aabb));
        newParent.parent = oldParent;
        newParent.height = sibling.height + 1;

        if (oldParent != null)
        {
            // The sibling was not the root.
            if (oldParent.child1 == sibling)
            {
                oldParent.child1 = newParent;
            } else
            {
                oldParent.child2 = newParent;
            }

            newParent.child1 = sibling;
            newParent.child2 = leaf;
            sibling.parent = newParent;
            leaf.parent = newParent;
        } else
        {
            // The sibling was the root.
            newParent.child1 = sibling;
            newParent.child2 = leaf;
            sibling.parent = newParent;
            leaf.parent = newParent;
            root = newParent;
        }

        // Walk back up the tree fixing heights and AABBs
        node = leaf.parent;
        while (node != null)
        {
            node = balance(node);

            Node child1 = node.child1;
            Node child2 = node.child2;

            node.height = 1 + Math.max(child1.height, child2.height);
            node.aabb = child1.aabb.combine(child2.aabb);

            node = node.parent;
        }
    }

    public void remove(T e)
    {
        Node leaf = e.node;
        e.node = null;
        if (leaf == root)
        {
            root = null;
            return;
        }

        Node parent = leaf.parent;
        Node grandParent = parent.parent;
        Node sibling;
        if (parent.child1 == leaf)
        {
            sibling = parent.child2;
        } else
        {
            sibling = parent.child1;
        }

        if (grandParent != null)
        {
            // Destroy parent and connect sibling to grandParent.
            if (grandParent.child1 == parent)
            {
                grandParent.child1 = sibling;
            } else
            {
                grandParent.child2 = sibling;
            }
            sibling.parent = grandParent;

            // Adjust ancestor bounds.
            Node index = grandParent;
            while (index != null)
            {
                index = balance(index);

                Node child1 = index.child1;
                Node child2 = index.child2;

                index.aabb = child1.aabb.combine(child2.aabb);
                index.height = 1 + Math.max(child1.height, child2.height);

                index = index.parent;
            }
        } else
        {
            root = sibling;
            sibling.parent = null;
        }
    }

    private Node balance(Node A)
    {
        if (A.isLeaf() || A.height < 2)
        {
            return A;
        }

        Node B = A.child1;
        Node C = A.child2;

        int balance = C.height - B.height;

        // Rotate C up
        if (balance > 1)
        {
            Node F = C.child1;
            Node G = C.child2;

            // Swap A and C
            C.child1 = A;
            C.parent = A.parent;
            A.parent = C;

            // A's old parent should point to C
            if (C.parent != null)
            {
                if (C.parent.child1 == A)
                {
                    C.parent.child1 = C;
                } else
                {
                    C.parent.child2 = C;
                }
            } else
            {
                root = C;
            }

            // Rotate
            if (F.height > G.height)
            {
                C.child2 = F;
                A.child2 = G;
                G.parent = A;
                A.aabb = B.aabb.combine(G.aabb);
                C.aabb = A.aabb.combine(F.aabb);

                A.height = 1 + Math.max(B.height, G.height);
                C.height = 1 + Math.max(A.height, F.height);
            } else
            {
                C.child2 = G;
                A.child2 = F;
                F.parent = A;
                A.aabb = B.aabb.combine(F.aabb);
                C.aabb = A.aabb.combine(G.aabb);

                A.height = 1 + Math.max(B.height, F.height);
                C.height = 1 + Math.max(A.height, G.height);
            }

            return C;
        }

        // Rotate B up
        if (balance < -1)
        {
            Node D = B.child1;
            Node E = B.child2;

            // Swap A and B
            B.child1 = A;
            B.parent = A.parent;
            A.parent = B;

            // A's old parent should point to B
            if (B.parent != null)
            {
                if (B.parent.child1 == A)
                {
                    B.parent.child1 = B;
                } else
                {
                    B.parent.child2 = B;
                }
            } else
            {
                root = B;
            }

            // Rotate
            if (D.height > E.height)
            {
                B.child2 = D;
                A.child1 = E;
                E.parent = A;
                A.aabb = C.aabb.combine(E.aabb);
                B.aabb = A.aabb.combine(D.aabb);

                A.height = 1 + Math.max(C.height, E.height);
                B.height = 1 + Math.max(A.height, D.height);
            } else
            {
                B.child2 = E;
                A.child1 = D;
                D.parent = A;
                A.aabb = C.aabb.combine(D.aabb);
                B.aabb = A.aabb.combine(E.aabb);

                A.height = 1 + Math.max(C.height, D.height);
                B.height = 1 + Math.max(A.height, E.height);
            }
            return B;
        }
        return A;
    }

    public class Tree2Dquery implements Iterable<T>
    {
        private AABBrect area;

        public Tree2Dquery(AABBrect area)
        {
            this.area = area;
        }

        public Iterator<T> iterator()
        {
            return new Tree2Diterator(area);
        }
    }

    private class Tree2Diterator implements Iterator<T>
    {
        private AABBrect area;
        private Stack<Node> stack;
        private T ret;

        public Tree2Diterator(AABBrect area)
        {
            this.area = area;
            stack = new Stack<Node>();
            if (root != null)
                stack.add(root);
        }

        @SuppressWarnings("unchecked")
        public boolean hasNext()
        {
            if (ret != null)
                return true;
            while (stack.size() > 0)
            {
                Node n = stack.pop();
                if (n.aabb.overlap(area))
                {
                    if (n.isLeaf())
                    {
                        ret = (T) n.aabb;
                        return true;
                    } else
                    {
                        stack.push(n.child1);
                        stack.push(n.child2);
                    }
                }
            }
            return false;
        }

        public T next()
        {
            if (!hasNext())
                return null;
            T r = ret;
            ret = null;
            return r;
        }

        public void remove()
        {
        }
    }

    public Tree2Dquery query(AABBrect treeAABB)
    {
        return new Tree2Dquery(treeAABB);
    }
}

