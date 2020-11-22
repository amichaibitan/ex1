package ex1.src;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public class WGraph_DS implements weighted_graph {
    private HashMap<Integer, node_info> kodkod;
    private  static int size_edge=0,mc=0,id=0;
    private HashMap<Integer,HashMap<Integer,Double>> kod_ni;

    public WGraph_DS(){
        size_edge=0;
        mc=0;
        kodkod = new HashMap<Integer,node_info>();
        kod_ni = new HashMap<Integer,HashMap<Integer,Double>>();
    }
    /**
     * if the node exist
     * @param key - the node_id
     * @return
     */
    @Override
    public node_info getNode(int key) {
        if(kodkod.containsKey(key))
            return kodkod.get(key);
        return null;
    }

    /**
     * check if the nodes exist in both hashmap
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(kodkod.containsKey(node1) && kodkod.containsKey(node2)
                && kod_ni.get(node1).containsKey(node2) &&
                kod_ni.get(node2).containsKey(node1)&& node1!=node2){
            return true;
        }
        return false;
    }

    /**
     * return the edge if it exist=w
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1, node2) && node1 != node2)
            return kod_ni.get(node1).get(node2);
        return -1;
    }

    /**
     * add a new node but if it is already exist
     * nothing will change
     * @param key
     */
    @Override
    public void addNode(int key) {
        if(!kodkod.containsKey(key)) {
            node_info node = new NodeInfo(key);
            kodkod.put(key,node);
           HashMap ni = new HashMap<Integer,Double>();
            kod_ni.put(key,ni);
        }

    }

    /**
     * connect two nodes and give them the weight if he is >=0
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (!kod_ni.get(node1).containsKey(node2) &&
                !kod_ni.get(node2).containsKey(node1) && w>=0 && node1!=node2){
            kod_ni.get(node1).put(node2,w);
            kod_ni.get(node2).put(node1,w);
            size_edge++;
            mc++;
        }
    }

    @Override
    public Collection<node_info> getV() {
        return kodkod.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        Collection<node_info> q = new Stack();
        for (int node:kod_ni.get(node_id).keySet()){
            q.add(getNode(node));
        }
        return q;
    }
    @Override
    public node_info removeNode(int key) {
        if(kodkod.containsKey(key)) {
            node_info temp= new NodeInfo(key);
            node_info node = new NodeInfo(key);
            for (node_info ni: getV(node.getKey())) {
                removeEdge(node.getKey(),ni.getKey());
            }
            kodkod.remove(key);
            mc++;
            return temp;
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1,node2) && hasEdge(node2,node1) && node1 != node2){
            kod_ni.get(node1).remove(node2);
            kod_ni.get(node2).remove(node1);
            size_edge--;
            mc++;
        }

    }

    @Override
    public int nodeSize() {
        return kodkod.size();
    }

    @Override
    public int edgeSize() {
        return size_edge;
    }

    @Override
    public int getMC() {
        return mc;
    }

    public static class NodeInfo implements node_info {
        private int key;
        private double tag;
        private String info;

         public NodeInfo(int key){
             this.key=key;
         }
        public NodeInfo(){
            this.key=id++;
        }

        @Override
        public int getKey() {
            return key;
        }

        @Override
        public String getInfo() {
            return info;
        }

        @Override
        public void setInfo(String s) {
            this.info=s;
        }

        @Override
        public double getTag() {
            return tag;
        }

        @Override
        public void setTag(double t) {
            this.tag=t;

        }

        }
}
