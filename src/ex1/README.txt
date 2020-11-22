package ex1;


in this code im building an undirected weighted graph. for that at first we begin to built
a node info that will give us a unique key,tag and info on the node.
we will put all the information in Data Structure- Hashmap.
in this case we will put hashmap in a hashmap. first is the key after that the neigbor and last the weight.


class node_info:
in the class node_data me bulid all the constructers for the keys,tags and info and for the hashmap.
 and we put that class inside the WGraph_DS.



class WGraph_DS:
we reset mc(every action that we do in the graph) and size_edge to zero.we make a hashmapand noed_data node.
we make a construstur that make reset everything.and a hashmap in a hashmap.

getnode(int key)-
if the kodkod(hashmap) it contains a key than return the key.

hasEdge(int node1, int node2)-
 if kod contains node1,node2 and node1!=node2
then it means that there edge between the 2 nodes.


getedge-
if node1,2 are neighbers return the weight.

addNode(node_data n)-
if that key does not exiest already in the graph than you can add it.

connect(int node1, int node2)-
after we check that they both have keys and not the same we make them to be
neighbers and we add size_edge++ and mc++.


Collection<node_data> getV()-
return a pointer that show all the nodes in the graph.

Collection<node_data> getV(int node_id)-
give you all the connection to a node id.by foreach.

 removeNode(int key)-
use the node_data n to be the node you want to remove and if he isnt null and exists then
foreach node you go to all his neighbers and remove them connection with each other.
mc++ and after that you remove that node fron the hashmap.

removeEdge(int node1, int node2)-

delete the connection between those 2 nodes, edge_size--, mc++.

nodesize()- how many nodes.

edgeSize-how many edges.

getMC()- how many action you had like add ,connection or remove.



class Graph_Algo:
this calss will show you 5 things:
1)deep copy
2)init(graph)
3)isconnected-if there is connection between every nodes in some way.
4)int of the shorted path.
5)what are the nodes for the shorted path.
6)save
7)load

we make new hashmap-copy1.
new gr=gr.

init (graph g)- if the graph isnt null then it will iniete the graph.

copy()-
do deep copy from the graph ds to graph algo.
first you the the first node and then you go to all hie neighbers in a for.



isconnected()-
build queue in linkedlist.
count=0-see how many nodes are in the queue.
we will use here Bfs algo, first we need to get a all tags to be zero.
if nodeSize is 1 || 0 then it is true
else we get some random nodes and changes his tag to 1 and that is our fn(node)
if fn isnt nul- we count++ and puo him in the tor.
after that we make a while - until the tor isnt empty we get out fn from the tor and
fn check if all his neighbers didnt changes yet and got into the tor,
if the didnt you get them inside the tor and count++,tor.add.and change his tag
than you dint get double.
if the count== gr.nodeSize it means that the are connected else false.


shortestPathDist(int src, int dest)-
also here we use the bfs algo.
if iscoonected is false then it is -1;
else all the tag should be -1.
we check that src exist and changes his tag to 0.
build new queue tor-linkedlist.
the node goes to all his neighbers and take the less weighted. and get the tag before with
them weight and this is how you get the  shorted path.

List<node_data> shortestPath(int src, int dest)-

retirn the shortest path with the nodes in order.
make a list=tor.
if iscoonected ==false return null.
if gr,nodeSize=0 return only tor and if src==dest retirn tor with only src.
int x=shoredpath.
build a stack and give him the last=dest node.
we begin in the dest and get to the next one if his tag +wieght== tag of the dest and then you
puthim in the queue.


save-
save your graph.

load-
 you load the graph from the doc and make sure he is correct.
