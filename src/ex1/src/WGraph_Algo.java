package ex1.src;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class WGraph_Algo implements weighted_graph_algorithms{
    private weighted_graph gr;

    @Override
    public void init(weighted_graph g) {
       if(g!=null)
           this.gr = g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.gr;
    }

    /**
     * deep copy of a graph.
     * @return copy1
     */
    @Override
    public weighted_graph copy() {

        weighted_graph copy1 = new WGraph_DS();
        for (node_info n: gr.getV() ) {
            copy1.addNode(n.getKey());
        }
        for (node_info n: gr.getV() ){
            for (node_info n1: gr.getV(n.getKey()) )
                copy1.connect(n.getKey(), n1.getKey(),gr.getEdge(n.getKey(),n1.getKey()));
        }
        return copy1;
    }

    /**
     * make sure all the nodes are connectsd in some way.
     * @return
     */
    @Override
    public boolean isConnected() {
        node_info fn = null;
        int count=0;
        Queue<node_info> tor = new LinkedList();
        for (node_info ver : gr.getV()) {
            ver.setTag(0);
        }
        if (gr.nodeSize() == 0 || gr.nodeSize() == 1){
            return true;}
        for (node_info node : gr.getV()) {
            fn = node;
            fn.setTag(1);
            break;
        }

        if(fn!=null) {
            count++;
            tor.add(fn);
            while (!tor.isEmpty()) {
                fn = tor.poll();
                for (node_info ni2 : gr.getV(fn.getKey())) {
                    if (ni2.getTag() == 0) {
                        ni2.setTag(1);
                        count++;
                        tor.add(ni2);
                    }
                }
                if (count == gr.nodeSize()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * give you the shorted path between src to dest but by weight
     * and not by nodes.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        double d = Double.MAX_VALUE;
        node_info first =gr.getNode(src);
        if(first==null || gr.getNode(dest)==null)
            return -1;
        if(src==dest)
            return 0;

        for (node_info ver : gr.getV()) {
            ver.setTag(-1);
        }
        for (node_info node : gr.getV()) {
            if (src == first.getKey()) {
                first.setTag(0);
                break;
            }
        }
        Queue<node_info> tor = new LinkedList();

        if(first!=null) {
            tor.add(first);
            while (!tor.isEmpty()) {
                first = tor.poll();
                for (node_info ni2 : gr.getV(first.getKey())) {
                    if (ni2.getTag() == -1){
                        tor.add(ni2);
                        ni2.setTag(first.getTag()+gr.getEdge(first.getKey(),ni2.getKey()));
                    }
                    if(ni2.getTag() > first.getTag()+ gr.getEdge(first.getKey(),ni2.getKey())) {
                        ni2.setTag(first.getTag()+gr.getEdge(first.getKey(),ni2.getKey()));

                    }
                }
            }
            node_info last =gr.getNode(dest);
            for (node_info node : gr.getV()) {
                if (dest == last.getKey()){
                    break;
                }
            }
            d= last.getTag();
        }
        return d;
    }

    /**
     * give the list of the nodes from the shorted path.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {

        List<node_info> tor = new LinkedList<node_info>();
        if (gr.nodeSize() == 0 ) {

            return tor;
        }
        if(src==dest){
            tor.add(gr.getNode(src));
            return tor;
        }
        double x=shortestPathDist(src,dest);
        Stack<node_info> stack = new Stack<node_info>();
        node_info last =gr.getNode(dest);
        stack.push(last);
        for ( int i=0; i <= gr.nodeSize() ; i++)
        {
            for(node_info node: gr.getV())
                if (node.getTag()+ gr.getEdge(node.getKey(),last.getKey()) == last.getTag()) {
                    stack.push(node);
                    last = node;
                    break;
                }
        }
        List<node_info> tor1 = new LinkedList<node_info>();
        while (!stack.isEmpty()){
            tor1.add(stack.pop());
        }

        return tor1;
    }



    /**
     *  it just save the graph in a file doc.
     * @param file - the file name (may include a relative path).
     * @return true
     */
    @Override// i took this code from class.
    public boolean save(String file) {
        try {
            PrintWriter pw = new PrintWriter(new File(file));
            StringBuilder sb = new StringBuilder();
            for (node_info node : gr.getV()) {
                for (node_info node1 : gr.getV(node.getKey())) {
                    if (node.getKey() != node1.getKey()) {
                        sb.append(node.getKey());
                        sb.append(",");
                        sb.append(node1.getKey());
                        sb.append(",");
                        sb.append(gr.getEdge(node.getKey(), node1.getKey()));
                        sb.append("\n");
                        pw.write(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
            pw.close();
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *  it load the graph from the file doc.
     * he is the same as the original.
     * @param file - file name
     * @return true
     */
    @Override
    public boolean load(String file) {
        weighted_graph g= new WGraph_DS();
        String line="";
        try {
            BufferedReader br= new BufferedReader(new FileReader(file));
            while((line=br.readLine()) != null){
                String [] info=line.split(",");
                int x= Integer.parseInt(info[0]);
                g.addNode(x);
                int y=Integer.parseInt(info[1]);
                g.addNode(y);
                double z=Double.parseDouble(info[2]);
                g.connect(x,y,z);
                System.out.println(info[0]+"," + info[1]+"," + info[2]);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
return true;
    }

}

