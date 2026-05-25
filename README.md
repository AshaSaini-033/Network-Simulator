Network Simulator (Java)

this is a simple network simulator made in java to understand basic networking concepts like hub, switch, error control and sliding window.

i didnt focus too much on UI or anything fancy, main goal was just to see how data flows between devices and how different topology works.

what this project does
simulate direct connection between 2 devices
star topology using hub
star topology using switch
connection of 2 hubs using a switch
basic error control using parity
sliding window for data transfer

it is menu driven so you can just run and choose what you want to test.

how to run
compile the code
javac Main.java
run it
java Main
choose option from menu
features (basic)
shows how data is sent from one pc to another
hub sends data to all (broadcast)
switch sends data to specific device
error detection using parity bit (simple)
sliding window sends data in parts
concepts used
oops (classes like EndDevice, Hub, Switch etc)
basic networking ideas
arrays and loops
simple logic (nothing too complex)
limitations
not real networking, just simulation
no GUI (only console output)
error control is very basic
not handling all edge cases
why i made this

this was made for learning purpose and college assignment.
i wanted to understand difference between hub and switch in a practical way instead of just theory.

note

code is kept simple on purpose so it is easy to read and understand.
not optimised or anything like that.


## Submission 2 - Network Layer

Implemented:
- Router creation
- IPv4 addressing
- ARP table
- Static routing
- RIP protocol simulation
- Packet forwarding
- Longest mask matching

the simulator was extended to implement major Network Layer functionalities.  
The main objective of this submission was to simulate how routers forward packets between different networks using IP addressing and routing techniques.

### Functionalities Implemented

#### 1. Router Creation and Configuration
A Router class was implemented to simulate the behaviour of a real network router.  
The router maintains:
- Routing table
- ARP table
- Packet forwarding logic

Routers can be connected with switches and end devices to create different network topologies.

Example:
```java
Router r1 = new Router("Router1");

---

## Submission 3 - Transport + Application Layer

Implemented:
- Port assignment
- Process to process communication
- Segment creation
- Sliding window flow control
- Chat application
- File transfer service
## Submission 3 - Transport + Application Layer

Submission 3 focused on implementing Transport Layer and Application Layer functionalities in the network simulator.  
The objective of this phase was to simulate process-to-process communication, flow control mechanisms and application services running over the network.

The simulator demonstrates how user applications communicate using transport layer protocols and how data moves through the protocol stack.

### Functionalities Implemented

#### Port Assignment
Port numbers were assigned to different applications/processes running on end devices.

This simulates how transport layer protocols identify different services running on a system.

Example:
```java
pc1.assignPort(
        5000,
        "chat"
);
