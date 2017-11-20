# Agentes Inteligentes
uMinho exercises for inteligent agents.

### 01 Message Sending

In this exercise we should have two agents, one who sends a message and other who receives it.

Key points here:

* You must follow the packages to start your agent classes, like MyAgents.Receiver(Just left everything as root(default) to avoid headaches)
* Java has weird things like nested classes. Behaviours usually are nested classes inside the agents.

### 02 Book borrowing

We were expected to create a sytem with to main kind of agents: a Librarian who has a list of  given books and a Borrower who choose the desired book randomly from a given list of books(in which some of them are not present in the librarian list of books). The borrower should ask the librarian for the book, and in case of having the asked book, it should be borrowed, make unavailable for renting until devolution, which should heappens in 10 seconds. New borrowers should apper in every second.

* TickerBehaviour to create  the Borrower Agents.
